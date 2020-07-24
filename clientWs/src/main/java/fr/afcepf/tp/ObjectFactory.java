
package fr.afcepf.tp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.afcepf.tp package. 
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

    private final static QName _GetAuthorName_QNAME = new QName("http://tp.afcepf.fr/", "getAuthorName");
    private final static QName _GetAuthorNameResponse_QNAME = new QName("http://tp.afcepf.fr/", "getAuthorNameResponse");
    private final static QName _Tva_QNAME = new QName("http://tp.afcepf.fr/", "tva");
    private final static QName _TvaResponse_QNAME = new QName("http://tp.afcepf.fr/", "tvaResponse");
    private final static QName _TtcResponse_QNAME = new QName("http://tp.afcepf.fr/", "ttcResponse");
    private final static QName _Ttc_QNAME = new QName("http://tp.afcepf.fr/", "ttc");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.afcepf.tp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Ttc }
     * 
     */
    public Ttc createTtc() {
        return new Ttc();
    }

    /**
     * Create an instance of {@link TtcResponse }
     * 
     */
    public TtcResponse createTtcResponse() {
        return new TtcResponse();
    }

    /**
     * Create an instance of {@link TvaResponse }
     * 
     */
    public TvaResponse createTvaResponse() {
        return new TvaResponse();
    }

    /**
     * Create an instance of {@link GetAuthorName }
     * 
     */
    public GetAuthorName createGetAuthorName() {
        return new GetAuthorName();
    }

    /**
     * Create an instance of {@link GetAuthorNameResponse }
     * 
     */
    public GetAuthorNameResponse createGetAuthorNameResponse() {
        return new GetAuthorNameResponse();
    }

    /**
     * Create an instance of {@link Tva }
     * 
     */
    public Tva createTva() {
        return new Tva();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tp.afcepf.fr/", name = "getAuthorName")
    public JAXBElement<GetAuthorName> createGetAuthorName(GetAuthorName value) {
        return new JAXBElement<GetAuthorName>(_GetAuthorName_QNAME, GetAuthorName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tp.afcepf.fr/", name = "getAuthorNameResponse")
    public JAXBElement<GetAuthorNameResponse> createGetAuthorNameResponse(GetAuthorNameResponse value) {
        return new JAXBElement<GetAuthorNameResponse>(_GetAuthorNameResponse_QNAME, GetAuthorNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tva }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tp.afcepf.fr/", name = "tva")
    public JAXBElement<Tva> createTva(Tva value) {
        return new JAXBElement<Tva>(_Tva_QNAME, Tva.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TvaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tp.afcepf.fr/", name = "tvaResponse")
    public JAXBElement<TvaResponse> createTvaResponse(TvaResponse value) {
        return new JAXBElement<TvaResponse>(_TvaResponse_QNAME, TvaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TtcResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tp.afcepf.fr/", name = "ttcResponse")
    public JAXBElement<TtcResponse> createTtcResponse(TtcResponse value) {
        return new JAXBElement<TtcResponse>(_TtcResponse_QNAME, TtcResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ttc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tp.afcepf.fr/", name = "ttc")
    public JAXBElement<Ttc> createTtc(Ttc value) {
        return new JAXBElement<Ttc>(_Ttc_QNAME, Ttc.class, null, value);
    }

}
