package cz.karelstefan.tasklist.domain;

public class Message {

    public enum Type {
        ERROR, SUCCESS
    }

    private final Type type;

    private final String text;

    public Message(Type type, String message) {
        this.type = type;
        this.text = message;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
