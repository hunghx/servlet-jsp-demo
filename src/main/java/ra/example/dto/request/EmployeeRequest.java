package ra.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Part;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private Integer id;
    private String name;
    private Date dob;
    private Boolean sex;
    private Part file;
}
