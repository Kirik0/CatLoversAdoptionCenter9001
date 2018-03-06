
package com.revature.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.revature.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllCats_QNAME = new QName("http://service.revature.com/", "getAllCats");
    private final static QName _TomCatException_QNAME = new QName("http://service.revature.com/", "TomCatException");
    private final static QName _AddCatResponse_QNAME = new QName("http://service.revature.com/", "addCatResponse");
    private final static QName _AddCat_QNAME = new QName("http://service.revature.com/", "addCat");
    private final static QName _GetAllCatsResponse_QNAME = new QName("http://service.revature.com/", "getAllCatsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.revature.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddCat }
     * 
     */
    public AddCat createAddCat() {
        return new AddCat();
    }

    /**
     * Create an instance of {@link GetAllCatsResponse }
     * 
     */
    public GetAllCatsResponse createGetAllCatsResponse() {
        return new GetAllCatsResponse();
    }

    /**
     * Create an instance of {@link AddCatResponse }
     * 
     */
    public AddCatResponse createAddCatResponse() {
        return new AddCatResponse();
    }

    /**
     * Create an instance of {@link TomCatException }
     * 
     */
    public TomCatException createTomCatException() {
        return new TomCatException();
    }

    /**
     * Create an instance of {@link GetAllCats }
     * 
     */
    public GetAllCats createGetAllCats() {
        return new GetAllCats();
    }

    /**
     * Create an instance of {@link Cat }
     * 
     */
    public Cat createCat() {
        return new Cat();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.revature.com/", name = "getAllCats")
    public JAXBElement<GetAllCats> createGetAllCats(GetAllCats value) {
        return new JAXBElement<GetAllCats>(_GetAllCats_QNAME, GetAllCats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TomCatException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.revature.com/", name = "TomCatException")
    public JAXBElement<TomCatException> createTomCatException(TomCatException value) {
        return new JAXBElement<TomCatException>(_TomCatException_QNAME, TomCatException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCatResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.revature.com/", name = "addCatResponse")
    public JAXBElement<AddCatResponse> createAddCatResponse(AddCatResponse value) {
        return new JAXBElement<AddCatResponse>(_AddCatResponse_QNAME, AddCatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.revature.com/", name = "addCat")
    public JAXBElement<AddCat> createAddCat(AddCat value) {
        return new JAXBElement<AddCat>(_AddCat_QNAME, AddCat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCatsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.revature.com/", name = "getAllCatsResponse")
    public JAXBElement<GetAllCatsResponse> createGetAllCatsResponse(GetAllCatsResponse value) {
        return new JAXBElement<GetAllCatsResponse>(_GetAllCatsResponse_QNAME, GetAllCatsResponse.class, null, value);
    }

}
