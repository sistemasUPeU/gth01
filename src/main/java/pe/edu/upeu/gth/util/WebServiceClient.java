/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.util;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import pe.edu.upeu.gth.properties.globalProperties;

/**
 *
 * @author Cesar
 */
public class WebServiceClient {
    public static JSONArray getData(String semestre, String methodProperties[]) throws Exception {
        Calendar calendario = new GregorianCalendar();
        String hour = String.format("%02d", calendario.get(Calendar.HOUR_OF_DAY));
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        // Send SOAP Message to SOAP Server
        String keyPub = StringMD.getStringMessageDigest(globalProperties.keyApp + hour, StringMD.MD5);
        System.out.println("Hora:" + hour);
        System.out.println(globalProperties.service + keyPub);
        System.out.println(globalProperties.keyApp + hour);
        SOAPMessage soapResponse = null;
        JSONObject jsonObject = null;
        JSONArray arr = null;
        try {
            System.out.println("enter to this line 1");
            soapResponse = soapConnection.call(createSOAPRequest(semestre, methodProperties), globalProperties.service + keyPub);
            if (soapResponse != null) {
                // print SOAP Response
                System.out.println("enter to this line 2");
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                soapResponse.writeTo(out);
                String strMsg = new String(out.toByteArray());
                jsonObject = XML.toJSONObject(strMsg);
                //System.out.println(jsonObject);
                arr = jsonObject.getJSONObject("SOAP-ENV:Envelope").
                        getJSONObject("SOAP-ENV:Body").getJSONObject(methodProperties[2]).
                        getJSONObject("return").
                        getJSONArray("item");
                soapConnection.close();
                System.out.println("tamaño arr:" + arr.length());
            }
        } catch (SOAPException e) {
            System.out.println(e);
            System.out.println("Error de conexion, intentelo nuevamente");
        }
        return arr;
    }
    public static SOAPMessage createSOAPRequest(String semestre, String methodProperties[]) {
        SOAPMessage soapMessage = null;
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            // SOAP Envelope
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration(methodProperties[1], globalProperties.serverURI);
            // SOAP Body
            SOAPBody soapBody = envelope.getBody();
            SOAPElement soapBodyElem = soapBody.addChildElement(methodProperties[0], methodProperties[1]);
            SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("key", methodProperties[1]);
            soapBodyElem1.addTextNode(globalProperties.keyID);
            SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("semestre", methodProperties[1]);
            soapBodyElem2.addTextNode(semestre);
            /*MimeHeaders headers = soapMessage.getMimeHeaders();
             headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");
             */
            System.out.println("enter to line 68");
            soapMessage.saveChanges();
            System.out.println("enter to line 70");
            /* Print the request message */
 /*System.out.println("Request SOAP Message:");
            soapMessage.writeTo(System.out);
            System.out.println();*/

        } catch (SOAPException e) {
            System.out.println("Error in createSOAPRequest:");
            e.printStackTrace();
        }
        return soapMessage;
    }
}
