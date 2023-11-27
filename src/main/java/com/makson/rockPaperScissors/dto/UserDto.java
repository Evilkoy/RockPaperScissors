package com.makson.rockPaperScissors.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    private String password;
    private Integer total;
    private Integer wins;
    private Integer draws;
    private Integer lost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id)
                && name.equals(userDto.name)
                && Objects.equals(this.password, userDto.password)
                && Objects.equals(this.total, userDto.total)
                && Objects.equals(this.wins, userDto.wins)
                && Objects.equals(this.draws, userDto.draws)
                && Objects.equals(this.lost, userDto.lost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, total, wins, draws, lost);
    }
}
