import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.LeitorXML;
import junit.framework.Assert;

public class LeitorXMLTest {

	@Test
	public void carregaXMLComUmaNegociacaoEmListaUnitaria() {
		String xmlTeste = "<list>" +
		          "  <negociacao>" +
		          "    <preco>43.5</preco>" +
		          "    <quantidade>1000</quantidade>" +
		          "    <data>" +
		          "      <time>1322233344455</time>" +
		          "    </data>" +
		          "  </negociacao>" +
		          "</list>";
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlTeste.getBytes());
		List<Negociacao> negociacao = leitor.carrega(xml);
		
		// testes
		Assert.assertEquals(1, negociacao.size());
		Assert.assertEquals(43.5, negociacao.get(0).getPreco());
		Assert.assertEquals(1000, negociacao.get(0).getQuantidade());
	}

}
