package secondProject.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="blogs")
public class Blogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private LocalDate dateOfPost;
    @Transient
    private boolean edited;
    @Nullable
    private LocalDate dateOfEdit;




}
