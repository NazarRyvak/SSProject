package entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Builder
public class ToDoList {
    private int id;
    private String description;
    private LocalDateTime dateBegin;
    private LocalDateTime dateEnd;
    private int user_id;
}
