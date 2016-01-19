$(function() {
    var taskTemplate = $("#task-template tr");
    var taskEditTemplate = $("#task-edit-template tr");
    var tasks = $("#tasks");
    var taskRestUrl = tasks.attr("data-task-rest-url");
    var taskListToken = tasks.attr("data-task-list-token");
    var taskRestCrudUrl = taskRestUrl + "/" + taskListToken;

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
            jsonAjax("PUT", taskRestCrudUrl + "/" + task.id, inputData).done(ajaxDone);
        } else {
            jsonAjax("POST", taskRestCrudUrl, inputData).done(ajaxDone);
        }
    });

    tasks.on("click", ".cancel-task", function(e) {
        var task = getClosestRow(this);
        if (task.id) {
            jsonAjax("GET", taskRestCrudUrl + "/" + task.id, {}).done(function(data) {
                task.row.before(createRowFromData(data));
                task.row.remove();
            });
        } else {
            task.row.remove();
        }
    });

    tasks.on("click", ".edit-task", function(e) {
        var rTask = getClosestRow(this);
        var eTask = taskEditTemplate.clone();
        eTask.attr("data-id", rTask.row.attr("data-id"));
        eTask.find("[name='text']").val(rTask.row.find(".task-text").html());
        eTask.find("[name='priority']").val(rTask.row.find(".task-priority").html());
        rTask.row.before(eTask);
        rTask.row.remove();
    });

    tasks.on("click", ".mark-done-task", function(e) {
        var task = getClosestRow(this);
        $.ajax({
            method: "PUT",
            url: taskRestUrl + "/mark-done/" + taskListToken + "/" + task.id,
        }).done(function() {
            task.row.remove();
        });
    });

    tasks.on("click", ".delete-task", function(e) {
        if (!confirm('Do you want to delete this task?')) {
            return;
        }

        var task = getClosestRow(this);
        $.ajax({
            method: "DELETE",
            url: taskRestCrudUrl + "/" + task.id,
        }).done(function() {
            task.row.remove();
        });
    });

    var getClosestRow = function(sel) {
        var task = {};
        task.row = $(sel).closest("tr");
        task.id = task.row.attr("data-id");
        return task;
    }

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
        row.find(".task-priority").html(taskData.priority);
        return row;
    }

    var getTaskData = function(task) {
        return {
            taskListToken: taskListToken,
            text: task.row.find("[name='text']").val(),
            priority: task.row.find("[name='priority']").val()
        };
    }

    var jsonAjax = function(method, url, data) {
        return $.ajax({
            method: method,
            url: url,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(data)
        })
    }

});

