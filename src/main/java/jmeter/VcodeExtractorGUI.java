package jmeter;
import org.apache.jmeter.processor.gui.AbstractPostProcessorGui;
import org.apache.jmeter.testelement.TestElement;

public class VcodeExtractorGUI extends AbstractPostProcessorGui {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @Override
    public TestElement createTestElement() {
        // TODO Auto-generated method stub
        VodeExtractor extractor = new VodeExtractor();
        modifyTestElement(extractor);
        return extractor;
    }

    @Override
    public String getLabelResource() {
        // TODO Auto-generated method stub
        return this.getClass().getName();
    }

    @Override
    public String getStaticLabel() {//设置显示名称
        // TODO Auto-generated method stub
        return "VcodeExtractor";
    }

    @Override
    public void modifyTestElement(TestElement extractor) {
        // TODO Auto-generated method stub
        super.configureTestElement(extractor);

    }
}

