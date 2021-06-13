package SmartphoneTests;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMeteo {


    @Test
    public void checkBoxSelected(){
        CheckboxGroup cbg = new CheckboxGroup();
        JPanel checkBoxPanel = new JPanel();
        Checkbox checkboxTest;
        Checkbox checkboxMetric = new Checkbox("metric",cbg,true);
        Checkbox checkboxImperial = new Checkbox("imperial",cbg,false);
        Checkbox checkboxStandard = new Checkbox("standard",cbg,false);

       assertEquals("metric",checkboxMetric.getLabel());
       assertEquals("imperial",checkboxImperial.getLabel());
       assertEquals("standard",checkboxStandard.getLabel());


       cbg.setSelectedCheckbox(checkboxMetric);

    }
}
