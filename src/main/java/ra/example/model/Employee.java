package ra.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    private Integer id;
    private String name;
    private Date dob;
    private Boolean sex;
    private String avatar;
    public String formatDate(){
       return new SimpleDateFormat("dd/MM/yyyy").format(dob);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + formatDate() +
                ", sex=" + sex +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
