package secondProject.Models;

import jakarta.persistence.*;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(length = 500000000)
    private byte[] photo;
    public Photo() {
        setDefaultPhoto();
    }

    public Photo(byte[] photo) {
        this.photo = photo;
    }


    public Long getId() {
        return id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        if(photo!=null)
            this.photo = photo;
        else setDefaultPhoto();
    }
    private void setDefaultPhoto() {
        try (InputStream inputStream = ResourceLoader.class.getResourceAsStream("/static/defaultPhoto.png")){
            if (inputStream != null)
                setPhoto(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
