package app.examen.ejercicio23grupo4;

/* Modelo de la clase para RecyclerView*/
public class ModelRecord {
    //Variables
    String id, name, image,addedTime, updatedTime;

    //Constructor

    public ModelRecord(String id, String name, String image,  String addedTime, String updatedTime) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
    }

    //Getter y Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
