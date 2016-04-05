package restaccessLayer;

/**
 * Created by Alex on 4/5/2016.
 */

public class Coupon
{

    public int id;

    public String name;

    public String about;

    public String category;

    public String address;

    public String phone;

    public double x_location;

    public double y_location;

    public String image;

    public int business_id;




    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAbout()
    {
        return this.about;
    }

    public void setAbout(String about)
    {
        this.about = about;
    }

    public String getCategory()
    {
        return this.category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public double getX_location()
    {
        return this.x_location;
    }

    public void setX_location(double x_location)
    {
        this.x_location = x_location;
    }

    public double getY_location()
    {
        return this.y_location;
    }

    public void setY_location(double y_location)
    {
        this.y_location = y_location;
    }

    public String getImage()
    {
        return this.image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String toString()
    {
        return String.format("id = %d, name = %s, about = %s business id = %s" , new Object[] {
                Integer.valueOf(this.id), this.name, this.about, this.business_id });
    }
}