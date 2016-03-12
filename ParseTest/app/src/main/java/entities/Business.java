package entities;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import java.io.Serializable;

/**
 * Created by elash on 14/12/2015.
 */
@ParseClassName("Business")
public class Business extends ParseObject implements Serializable{
    public String getName(){return getString("name");};
    public void setName(String name){put("name",name);};

    public String getAbout(){return getString("about");};
    public void setAbout(String about){put("about", about);};

    public String getPay(){return getString("pay");};
    public void setPay(String pay){put("pay", pay);};

    public String getCategory(){return getString("category");};
    public void setCategoty(String cat){put("category", cat);};

    public String getAddress(){return getString("address");};
    public void setAddress(String add){put("address", add);};

    public String getPhone(){return getString("phone");};
    public void setPhone(String phone){put("phone", phone);};

    public ParseGeoPoint getLocation(){return getParseGeoPoint("location");};
    public void setLocation(ParseGeoPoint loc){put("location", loc);};

    public String getArea(){return getString("area");};
    public void setArea(String area){put("area", area);};

    //public ParseFile getImage(){return getParseFile("image");};
    //public void setImage(ParseFile image){put("image", image);};
    public int getImage(){return getInt("image");};
    public void setImage(int image){put("image", image);};

    @Override
    public String toString() {
        return getName()+";\n"
                +getAbout()+";\n"
                +getPay()+";\n"
                +getCategory()+";\n"
                +getAddress()+";\n"
                +getPhone()+";\n"
                +getLocation()+";\n"
                +getArea()+";\n"
                +getImage();
    }
}
