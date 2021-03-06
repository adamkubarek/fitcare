package pl.javastyle.fitcare.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.category.Category;
import pl.javastyle.fitcare.core.BaseEntity;
import pl.javastyle.fitcare.domain.Diet;
import pl.javastyle.fitcare.domain.UserDietSettings;
import pl.javastyle.fitcare.domain.WeightJournal;
import pl.javastyle.fitcare.product.Product;
import pl.javastyle.fitcare.user.enums.ActivityRate;
import pl.javastyle.fitcare.user.enums.DietGoal;
import pl.javastyle.fitcare.user.enums.Gender;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends BaseEntity {

    private String name;
    private Integer age;
    private Integer height;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private DietGoal dietGoal;

    @Enumerated(EnumType.STRING)
    private ActivityRate activityRate;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    @OneToMany(mappedBy = "user")
    private List<Diet> diets;

    @OneToOne
    private UserDietSettings userDietSettings;

    @OneToOne
    private WeightJournal weightJournal;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Auth auth;

    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.age = userDTO.getAge();
        this.weight = userDTO.getWeight();
        this.height = userDTO.getHeight();
        this.activityRate = userDTO.getActivityRate();
        this.dietGoal = userDTO.getDietGoal();
        this.gender = userDTO.getGender();
    }
}
