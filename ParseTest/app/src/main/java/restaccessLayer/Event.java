package restaccessLayer;

/**
 * Created by Alex on 1/9/2016.
 */
public class Event {


    int id;

    String name;

    String about;

    String cost;

    String category;

    String address;

    String phone;

    float x_location;

    float y_location;

    String area;

    String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getX_location() {
        return x_location;
    }

    public void setX_location(float x_location) {
        this.x_location = x_location;
    }

    public float getY_location() {
        return y_location;
    }

    public void setY_location(float y_location) {
        this.y_location = y_location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format("id = %d, name = %s, about = %s "
                + "cost = %s", id, name, about, cost);

    }
}
