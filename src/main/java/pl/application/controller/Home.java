package pl.application.controller;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.application.dto.CsvDto;
import pl.application.dto.Requests;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Home {



    @GetMapping("")
    @ResponseBody
    public String hello() {
        return "Hello world";
    }

    //Creation Object from
    @GetMapping("csv")
    @ResponseBody
    public String csv() throws IOException {

         ClassLoader classLoader = getClass().getClassLoader();
         String path  = classLoader.getResource("/csv/example.csv").getPath();
        CsvDto csvDto = new CsvDto("a","b","c","d","e");
        /*String respath = "/csv/example.csv";*/
        /*InputStream in = Home.class.getResourceAsStream(respath);*/
/*
        InputStreamReader inr = new InputStreamReader(in, "UTF-8");
*/

        Reader reader = Files.newBufferedReader(Paths.get(path));

        CSVReader csvReader = new CSVReader(reader);
        List<String[]> records = csvReader.readAll();

        List<CsvDto> csvDtoList= new ArrayList<>();

        String[] s1 = records.get(0);
        String[] s2 = records.get(1);
        String[] s3 = records.get(2);
        String[] s4 = records.get(3);
        String[] s5 = records.get(4);


        for (int i =0; i < records.size(); i++) {
        String[] c1 = records.get(i);
        csvDto.setClientId(
                c1[0]);
        csvDto.setRequestId(c1[1]);
        csvDto.setName(c1[2]);
        csvDto.setQuantity(c1[3]);
        csvDto.setPrice(c1[4]);

        csvDtoList.add(new CsvDto(csvDto.getClientId(),csvDto.getRequestId(),csvDto.getName(),csvDto.getName(),csvDto.getPrice()));

        }

        return new Gson().toJson(csvDtoList);
    }


    //Creation xml file
    @GetMapping("xml")
    @ResponseBody
    public String adding () throws JAXBException, UnsupportedEncodingException {

        String respath = "/xml/1.xml";
        InputStream in = Home.class.getResourceAsStream(respath);


        InputStreamReader inr = new InputStreamReader(in, "UTF-8");



        File file = new File("/home/marcin/Pulpit/calicoder/CsvXml/src/main/resources/xml/1.xml");

        Requests requests = new Requests(null);
        JAXBContext jaxbContext = JAXBContext.newInstance(Requests.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(requests, file);
        jaxbMarshaller.marshal(requests, System.out);
        return "1.xml";
    }

    @GetMapping("xml1")
    @ResponseBody
    public String xml() throws JAXBException, UnsupportedEncodingException {

         ClassLoader classLoader = getClass().getClassLoader();
         String path  = classLoader.getResource("/xml/1.xml").getPath();

        JAXBContext jaxbContext = JAXBContext.newInstance(Requests.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Requests requests = (Requests) jaxbUnmarshaller.unmarshal(new File(path));
        return new Gson().toJson(requests);
    }
}

