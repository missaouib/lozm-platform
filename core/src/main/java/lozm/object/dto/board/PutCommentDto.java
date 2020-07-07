package lozm.object.dto.board;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PutCommentDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        private String name;

        private String contents;

        private String type;

        private Long amount;

        private Long quantity;

        private LocalDateTime startDt;

        private LocalDateTime endDt;

        private int flag;
    }

}
