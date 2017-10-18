package com.fincatto.documentofiscal.nfe;

import org.junit.Assert;
import org.junit.Test;

import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.classes.NFTipoEmissao;

import java.security.KeyStore;
import java.security.KeyStoreException;

public class NFeConfigTest {

    @Test
    public void testaParametrosPadrao() {
        final NFeConfigTeste config = new NFeConfigTeste();

        Assert.assertEquals("TLSv1", config.getSSLProtocolo());
        Assert.assertEquals(NFTipoEmissao.EMISSAO_NORMAL, config.getTipoEmissao());
        Assert.assertNull(config.getCodigoSegurancaContribuinte());
        Assert.assertNull(config.getCodigoSegurancaContribuinteID());
    }

    private class NFeConfigTeste extends NFeConfig {

        @Override
        public DFUnidadeFederativa getCUF() {
            return null;
        }

        @Override
        public KeyStore getCertificadoKeyStore() throws KeyStoreException {
            return null;
        }

        @Override
        public String getCertificadoSenha() {
            return null;
        }

        @Override
        public KeyStore getCadeiaCertificadosKeyStore() throws KeyStoreException {
            return null;
        }

        @Override
        public String getCadeiaCertificadosSenha() {
            return null;
        }

		@Override
		public String getCertificadoAlias(){
			return null;
		}
    }
}