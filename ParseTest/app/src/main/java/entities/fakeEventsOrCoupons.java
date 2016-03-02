package entities;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.parse.ParseFile;
import com.parse.ParseGeoPoint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import restaccessLayer.Event;
import restaccessLayer.RestAccessLayer;
import restaccessLayer.RestCallback;
import studioidan.com.parsetest.R;

/**
 * Created by elash on 15/12/2015.
 */
public class fakeEventsOrCoupons {

    static List<GenericEvent> businesses = null;

    private static List<GenericEvent> convert(List<Event> toConvert){
        List<GenericEvent> toReturn=new LinkedList<GenericEvent>();
       for(Event e: toConvert)
        toReturn.add(convert(e));
        return  toReturn;
    }

    private  static GenericEvent convert(Event e){
        GenericEvent ge =new GenericEvent();
       // ge.setImage(e.getImage());
        ge.setAddress(e.getAddress());
        ge.setLocation(new ParseGeoPoint(e.getX_location(), e.getY_location()));
        ge.setPhone(e.getPhone());
        ge.setAbout(e.getAbout());
        ge.setArea(e.getArea());
        ge.setName(e.getName());

        return ge;
    }

    public static List<GenericEvent> getFakeEvents(Fragment f){

        try {
            final List<GenericEvent> toReturn;
            RestAccessLayer rel = RestAccessLayer.getInstance(f.getActivity(), Environment.getExternalStorageDirectory() + "/config.properties");
            RestCallback<Event[]> rc = new RestCallback<Event[]>();
            RestCallback.OnResponseSuccess<List<Event>> success = new RestCallback.OnResponseSuccess<List<Event>>() {
                @Override
                public void onSuccess(List<Event> result, Fragment f) {
                    List<GenericEvent> toReturn = convert(result);
                    Log.i("All_Gadera", "Success Callback");

                }
            };
            rel.runJsonRequestGetEvent(success,f);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        /**** Return "SERVER" events ****/
        /*
        List<GenericEvent> toReturn=new ArrayList<GenericEvent>();
        Resources res = Resources.getSystem();

        GenericEvent optics = new GenericEvent();
        optics.setName("אופטיקה קשר עין");
        optics.setAddress("בן גוריון 105, מרכז המושבה, גדרה");
        optics.setAbout("רשת מכוני אופטיקה ואופטומטריה המתמחה בבדיקות ראייה והתאמת אביזרי ראייה");
        optics.setPhone("08-8669868");
        optics.setLocation(new ParseGeoPoint(31.814991, 34.774994));

        optics.setImage(R.drawable.optics_eye_contact);
        toReturn.add(optics);

        GenericEvent angloSaxon = new GenericEvent();
        angloSaxon.setName("אנגלו-סכסון");
        angloSaxon.setAddress("רח' פינס 5, גדרה");
        angloSaxon.setAbout("אנגלו-סכסון הינה מהחברות המובילות והוותיקות בישראל בתחום שיווק ותיווך נדל\"ן.\n" +
                "\n" +
                "אנגלו-סכסון בבעלות קבוצת ס.א.ל., פועלת באמצעות רשת סניפים ארצית המשווקת ומתווכת בכל סוגי הנכסים:\n" +
                "מכירה והשכרת דירות, בתים, משרדים, מבנים מסחריים, דירות יוקרה, וילות, מגרשים, חנויות, מחסנים, מבני תעשיה ועוד.");

        angloSaxon.setPhone("08-8601010");
        angloSaxon.setLocation(new ParseGeoPoint(31.815960, 34.777560));
        angloSaxon.setImage(R.drawable.angloc_saxon);
        toReturn.add(angloSaxon);

        GenericEvent vet = new GenericEvent();
        vet.setName("הולי - PET מרפאה וטרינרית");
        vet.setAbout("במרפאה ניתן לקבל טיפולים שגרתיים, טיפולי חירום, ניתוחים מכל הסוגים (על ידי מומחים כירורגיים במידת הצורך), שירותי אולטרסאונד, בדיקות דם ועוד.");
        vet.setAddress("הגורן 6 גדרה");
        vet.setPhone("054-4969116");
        vet.setLocation(new ParseGeoPoint(31.814042, 34.773045));
        vet.setImage(R.drawable.holi_pet);
        toReturn.add(vet);

        GenericEvent pixel = new GenericEvent();
        pixel.setName("פיסקל אלבומים דיגיטליים גרפיקה ודפוס");
        pixel.setAbout("אתר המתמחה בעיצוב, עריכה והדפסה\n" +
                "של מוצרים ייחודיים כגון:\n" +
                "\n" +
                "\n" +
                "rאלבומים דיגיטליים במבחר גדלים\n" +
                "\n" +
                "קנבסים במבחר גדלים\n" +
                "\n" +
                "לוחות שנה\n" +
                "\n" +
                "אלבומי מיני\n" +
                "\n" +
                "פלייסמטים\n" +
                "\n" +
                "כרטיסי ברכה לאירועים");
        pixel.setAddress("הגורן 19 גדרה");
        pixel.setPhone("08-8597027");
        pixel.setLocation(new ParseGeoPoint(31.814230, 34.773220));
        pixel.setImage(R.drawable.pixel);
        toReturn.add(pixel);

        GenericEvent clickMadlik = new GenericEvent();
        clickMadlik.setName("קליק מדליק");
        clickMadlik.setAbout("מתנות, צעצועים ומחלקת מכשירי כתיבה וציוד משרדי (ניירת מדפסות, כלי כתיבה ועוד) גם לעסקים!");
        clickMadlik.setAddress("רח' הבילויים, 17 גדרה");
        clickMadlik.setPhone("08-8683131");
        clickMadlik.setLocation(new ParseGeoPoint(31.815184, 34.773848));
        clickMadlik.setImage(R.drawable.click_madlik);
        toReturn.add(clickMadlik);

        GenericEvent interiorDesign = new GenericEvent();
        interiorDesign.setName("רויטל בן שבת מעצבת פנים");
        interiorDesign.setAbout("המשרד מתמחה בעיצוב דירות, בתים, חנויות, סטיילינג והלבשת הבית, צבע, תאורה וגבס. \n" +
                "יעוץ בבחירת פריטים לעיצוב כגון: חיפויים, ריצוף, ריהוט, פרטי נגרות, טפטים, כלים סניטריים, פרטי תאורה וכדומה תוך שימת דגש רב על פרטים וכל זאת תוך עמידה במסגרת תקציבית והתאמה לצרכי הלקוח. ");
        interiorDesign.setAddress("גדרה");
        interiorDesign.setPhone("054-7622240");
        interiorDesign.setLocation(new ParseGeoPoint(31.812301, 34.777019));
        interiorDesign.setImage(R.drawable.revital_interior_design);
        toReturn.add(interiorDesign);

        GenericEvent opticsBot = new GenericEvent();
        opticsBot.setName("אופטיקה בוטח");
        opticsBot.setAbout("אופטיקה בוטח הינה מכון מתקדם לאופטומטריה. ברכישת משקפי ראייה או עדשות מגע חשוב מאוד להיבדק ע\"י איש מקצוע אמין, הוגן\n" +
                "ומקצועי שידע להתאים עבורך את הפתרון הנכון והבריא לעיניים שלך!");

        opticsBot.setAddress("הבילויים 21 א' גדרה (מול בנק לאומי)");
        opticsBot.setPhone("08-8696099");
        opticsBot.setLocation(new ParseGeoPoint(31.812430, 34.778262));
        opticsBot.setImage(R.drawable.optics_bot);
        toReturn.add(opticsBot);
*/
        return  toReturn;
    }


    public static GenericEvent getFake(int i){
        return businesses.get(i);
    }

}
