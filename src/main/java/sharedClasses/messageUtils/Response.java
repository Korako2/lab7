package sharedClasses.messageUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@RequiredArgsConstructor
public class Response implements Serializable {
    @Getter
    private final String responseHead;
    @Getter
    private final String responseBody;
    public String toString() {
        return "Response[" + responseHead + ", " + responseBody + "]";
    }
}
