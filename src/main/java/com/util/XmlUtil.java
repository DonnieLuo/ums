package com.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Donnie on 2017/2/20.
 */
public class XmlUtil {
    public static String toXml(Object obj) {
        String xmlStr = null;
        JAXBContext jaxbContext = null;
        Marshaller marshaller = null;
        StringWriter writer = null;
        try {
            jaxbContext = JAXBContext.newInstance(obj.getClass());
            marshaller = jaxbContext.createMarshaller();
            writer = new StringWriter();
            marshaller.marshal(obj, writer);
            xmlStr = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlStr;
    }
    public static <T> T fromXml(String xmlStr, Class<T> _class) {
        T result = null;
        JAXBContext jaxbContext = null;
        try {
            StringReader reader = new StringReader(xmlStr);
            jaxbContext = JAXBContext.newInstance(_class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            result = (T) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
