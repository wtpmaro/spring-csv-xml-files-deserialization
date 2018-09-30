package pl.application.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "requests")
@XmlAccessorType(XmlAccessType.FIELD)
public class Requests {


    @XmlElement(name = "request")
    List<CsvDto> request = null;

    public List<CsvDto> getRequest() {
        return request;
    }

    public void setRequest(List<CsvDto> request) {
        this.request = request;
    }

    public Requests(){}

    public Requests(List<CsvDto> request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "request=" + request +
                '}';
    }
}
