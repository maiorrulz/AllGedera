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
        optics.setName("������� ��� ���");
        optics.setAddress("�� ������ 105, ���� ������, ����");
        optics.setAbout("��� ����� ������� ����������� ������ ������� ����� ������ ������ �����");
        optics.setPhone("08-8669868");
        optics.setLocation(new ParseGeoPoint(31.814991, 34.774994));

        optics.setImage(R.drawable.optics_eye_contact);
        toReturn.add(optics);

        GenericEvent angloSaxon = new GenericEvent();
        angloSaxon.setName("�����-�����");
        angloSaxon.setAddress("��' ���� 5, ����");
        angloSaxon.setAbout("�����-����� ���� ������� �������� ��������� ������ ����� ����� ������ ���\"�.\n" +
                "\n" +
                "�����-����� ������ ����� �.�.�., ����� ������� ��� ������ ����� ������� ������� ��� ���� ������:\n" +
                "����� ������ �����, ����, ������, ����� �������, ����� �����, �����, ������, ������, ������, ���� ����� ����.");

        angloSaxon.setPhone("08-8601010");
        angloSaxon.setLocation(new ParseGeoPoint(31.815960, 34.777560));
        angloSaxon.setImage(R.drawable.angloc_saxon);
        toReturn.add(angloSaxon);

        GenericEvent vet = new GenericEvent();
        vet.setName("���� - PET ����� ��������");
        vet.setAbout("������ ���� ���� ������� �������, ������ �����, ������� ��� ������ (�� ��� ������ ��������� ����� �����), ������ ����������, ������ �� ����.");
        vet.setAddress("����� 6 ����");
        vet.setPhone("054-4969116");
        vet.setLocation(new ParseGeoPoint(31.814042, 34.773045));
        vet.setImage(R.drawable.holi_pet);
        toReturn.add(vet);

        GenericEvent pixel = new GenericEvent();
        pixel.setName("����� ������� ��������� ������ �����");
        pixel.setAbout("��� ������ ������, ����� ������\n" +
                "�� ������ �������� ����:\n" +
                "\n" +
                "\n" +
                "r������� ��������� ����� �����\n" +
                "\n" +
                "������ ����� �����\n" +
                "\n" +
                "����� ���\n" +
                "\n" +
                "������ ����\n" +
                "\n" +
                "���������\n" +
                "\n" +
                "������ ���� ��������");
        pixel.setAddress("����� 19 ����");
        pixel.setPhone("08-8597027");
        pixel.setLocation(new ParseGeoPoint(31.814230, 34.773220));
        pixel.setImage(R.drawable.pixel);
        toReturn.add(pixel);

        GenericEvent clickMadlik = new GenericEvent();
        clickMadlik.setName("���� �����");
        clickMadlik.setAbout("�����, ������� ������ ������ ����� ����� ����� (����� ������, ��� ����� ����) �� ������!");
        clickMadlik.setAddress("��' ��������, 17 ����");
        clickMadlik.setPhone("08-8683131");
        clickMadlik.setLocation(new ParseGeoPoint(31.815184, 34.773848));
        clickMadlik.setImage(R.drawable.click_madlik);
        toReturn.add(clickMadlik);

        GenericEvent interiorDesign = new GenericEvent();
        interiorDesign.setName("����� �� ��� ����� ����");
        interiorDesign.setAbout("����� ����� ������ �����, ����, ������, �������� ������ ����, ���, ����� ����. \n" +
                "���� ������ ������ ������ ����: �������, �����, �����, ���� �����, �����, ���� ��������, ���� ����� ������ ��� ���� ��� �� �� ����� ��� ��� ��� ����� ������ ������� ������ ����� �����. ");
        interiorDesign.setAddress("����");
        interiorDesign.setPhone("054-7622240");
        interiorDesign.setLocation(new ParseGeoPoint(31.812301, 34.777019));
        interiorDesign.setImage(R.drawable.revital_interior_design);
        toReturn.add(interiorDesign);

        GenericEvent opticsBot = new GenericEvent();
        opticsBot.setName("������� ����");
        opticsBot.setAbout("������� ���� ���� ���� ����� �����������. ������ ����� ����� �� ����� ��� ���� ���� ������ �\"� ��� ����� ����, ����\n" +
                "������� ���� ������ ����� �� ������ ����� ������ ������� ���!");

        opticsBot.setAddress("�������� 21 �' ���� (��� ��� �����)");
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
