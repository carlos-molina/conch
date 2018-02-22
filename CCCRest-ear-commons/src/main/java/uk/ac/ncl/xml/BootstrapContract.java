package uk.ac.ncl.xml;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by isfyrakis on 06/11/14.
 */
@XmlRootElement(name = "kcontract")
public class BootstrapContract implements Serializable {
    private static final long serialVersionUID = 1L;
    private String businessOperation;
    private List<String> businessOperations;
    public BootstrapContract(){

    }




}
