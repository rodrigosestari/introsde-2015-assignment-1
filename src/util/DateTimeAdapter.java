package util;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;

public class DateTimeAdapter extends XmlAdapter<Date, DateTime> {

    @Override
    public DateTime unmarshal(Date v) throws Exception {
        return new DateTime(v.getTime());
    }

    @Override
    public Date marshal(DateTime v) throws Exception {
        return new Date(v.getMillis());
    }

}
