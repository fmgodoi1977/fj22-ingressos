package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public class DescontoDeTrintaPorcentoParaBancos implements Desconto {

	private BigDecimal percentualDeDesconto = new BigDecimal("0.3");

	@Override
	public BigDecimal aplicarValorSobre(BigDecimal precoOriginal) {

		return precoOriginal.subtract(trintaPorcentoSobre(precoOriginal));
	}

	private BigDecimal trintaPorcentoSobre(BigDecimal precoOriginal) {
		return precoOriginal.multiply(percentualDeDesconto);

	}

}
