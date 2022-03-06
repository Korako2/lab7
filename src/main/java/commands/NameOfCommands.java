package commands;

public enum NameOfCommands {
    HELP("help"),
    INFO("info"),
    SHOW("show"),
    ADD("add"),
    UPDATE("update"),
    REMOVE_BY_ID("remove_by_id"),
    CLEAR("clear"),
    SAVE("save"),
    EXECUTE_SCRIPT("execute_script"),
    EXIT("exit"),
    ADD_IF_MIN("add_if_min"),
    REMOVE_LOWER("remove_lower"),
    HISTORY("history"),
    FILTER_STARTS_WITH_DESCRIPTION("filter_starts_with_description description"),
    FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS("filter_less_than_number_of_participants"),
    PRINT_UNIQUE_GENRE("print_unique_genre");
    private String name;

    NameOfCommands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
