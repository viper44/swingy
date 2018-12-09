package model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class NewHeroRequest {

    @NotNull
    @NotEmpty(message = "Please enter Hero name")
    @Size(max = 10, min = 3, message = "hero name is invalid")
    private String name;
}
