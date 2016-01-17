$(function() {
    var taskTemplate = $("#task-template tr");
    var taskEditTemplate = $("#task-edit-template tr");
    var tasks = $("#tasks");
    var taskRestUrl = tasks.attr("data-task-rest-url");
    var taskListToken = tasks.attr("data-task-list-token");

    $("#add-task").on( "click", function(e) {
        var task = taskEditTemplate.clone();
        tasks.prepend(task);
    });

    tasks.on("click", ".save-task", function(e) {
        var task = getClosestRow(this);
        var inputData = getTaskData(task);
        var ajaxDone = function(data) {
            task.row.before(createRowFromData(data));
            task.row.remove();
        }

        if (!validateInputData(inputData)) {
            return;
        }

        if (task.id) {
            ajax("PUT", taskRestUrl + "/" + task.id, inputData).done(ajaxDone);
        } else {
            ajax("POST", taskRestUrl, inputData).done(ajaxDone);
        }
    });

    var validateInputData = function(data) {
        if (!data.text || !data.text.trim().length) {
            alert("The task's text is required");
            return false;
        }
        return true;
    }

    var createRowFromData = function(taskData) {
        var row = taskTemplate.clone();
        row.attr("data-id", taskData.id);
        row.find(".task-text").html(taskData.text);
        return row;
    }

    var getTaskData = function(task) {
        return {taskListToken: taskListToken, text: task.row.find("[name='text']").val()};
    }

    var ajax = function(method, url, data) {
        return $.ajax({
            method: method,
            url: url,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(data)
        })
    }

    tasks.on("click", ".cancel-task", function(e) {
        var task = getClosestRow(this);
        if (task.id) {

        } else {
            task.row.remove();
        }
    });

    tasks.on("click", ".edit-task", function(e) {
        var rTask = getClosestRow(this);
        var eTask = taskEditTemplate.clone();
        eTask.attr("data-id", rTask.row.attr("data-id"));
        eTask.find("[name='text']").val(rTask.row.find(".task-text").html());
        rTask.row.before(eTask);
        rTask.row.remove();
    });

    var getClosestRow = function(sel) {
        var task = {};
        task.row = $(sel).closest("tr");
        task.id = task.row.attr("data-id");
        return task;
    }
});

