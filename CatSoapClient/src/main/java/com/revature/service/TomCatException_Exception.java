
package com.revature.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "TomCatException", targetNamespace = "http://service.revature.com/")
public class TomCatException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private TomCatException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public TomCatException_Exception(String message, TomCatException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public TomCatException_Exception(String message, TomCatException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.revature.service.TomCatException
     */
    public TomCatException getFaultInfo() {
        return faultInfo;
    }

}