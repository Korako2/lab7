package commands.commandsUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import messageUtils.ResponseCode;

@RequiredArgsConstructor
public class CommandResult {
    @Getter
    private final String result;
    @Getter
    private final ResponseCode responseCode;
}
