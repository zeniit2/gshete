package com.fiap.mecatronica.baselunar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fiap.mecatronica.baselunar.model.AlertaOperacional;
import com.fiap.mecatronica.baselunar.model.Climatizacao;
import com.fiap.mecatronica.baselunar.model.ConsumoEnergia;
import com.fiap.mecatronica.baselunar.model.Reservatorio;
import com.fiap.mecatronica.baselunar.model.Sensor;
import com.fiap.mecatronica.baselunar.repository.AlertaOperacionalRepository;
import com.fiap.mecatronica.baselunar.repository.ClimatizacaoRepository;
import com.fiap.mecatronica.baselunar.repository.ConsumoEnergiaRepository;
import com.fiap.mecatronica.baselunar.repository.ReservatorioRepository;
import com.fiap.mecatronica.baselunar.repository.SensorRepository;

/**
 * Popula o banco H2 com dados iniciais na primeira execucao.
 * Como o banco esta em modo FILE, os dados sao inseridos apenas se as tabelas
 * estiverem vazias (evitando duplicacao a cada reinicio da aplicacao).
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ReservatorioRepository reservatorioRepository;

    @Autowired
    private ConsumoEnergiaRepository consumoEnergiaRepository;

    @Autowired
    private ClimatizacaoRepository climatizacaoRepository;

    @Autowired
    private AlertaOperacionalRepository alertaRepository;

    @Override
    public void run(String... args) {
        if (sensorRepository.count() > 0) {
            System.out.println("Banco ja inicializado. Pulando carga de dados de exemplo.");
            return;
        }

        System.out.println("Inicializando banco de dados da Base Lunar com dados de exemplo...");

        carregarSensores();
        carregarReservatorios();
        carregarConsumosEnergia();
        carregarClimatizacoes();
        carregarAlertas();

        System.out.println("Carga inicial concluida. Base Lunar pronta para monitoramento.");
    }

    private void carregarSensores() {
        sensorRepository.save(new Sensor(
                "Sensor de Oxigenio O2-01", "oxigenio",
                "Modulo de Habitacao A", "%", 20.9, 19.5, 23.5, true));
        sensorRepository.save(new Sensor(
                "Sensor de CO2 CD-02", "dioxido_carbono",
                "Modulo de Habitacao A", "ppm", 410.0, 0.0, 1000.0, true));
        sensorRepository.save(new Sensor(
                "Sensor de Pressao PR-03", "pressao",
                "Eclusa Principal", "kPa", 101.3, 95.0, 105.0, true));
        sensorRepository.save(new Sensor(
                "Sensor de Radiacao RD-04", "radiacao",
                "Casco Externo Norte", "uSv/h", 0.5, 0.0, 5.0, true));
        sensorRepository.save(new Sensor(
                "Sensor de Temperatura TM-05", "temperatura",
                "Laboratorio Cientifico", "C", 22.0, 18.0, 26.0, true));
    }

    private void carregarReservatorios() {
        reservatorioRepository.save(new Reservatorio(
                "Reservatorio de Agua Potavel", "agua", 5000.0, 3200.0, "litros", "Modulo de Suporte a Vida"));
        reservatorioRepository.save(new Reservatorio(
                "Tanque de Oxigenio Liquido", "oxigenio", 2000.0, 1450.0, "kg", "Modulo de Suporte a Vida"));
        reservatorioRepository.save(new Reservatorio(
                "Tanque de Combustivel (Hidrazina)", "combustivel", 1200.0, 380.0, "litros", "Plataforma de Pouso"));
        reservatorioRepository.save(new Reservatorio(
                "Reserva de Nitrogenio", "nitrogenio", 800.0, 760.0, "kg", "Modulo de Pressurizacao"));
    }

    private void carregarConsumosEnergia() {
        consumoEnergiaRepository.save(new ConsumoEnergia(
                "Modulo de Habitacao A", "solar", 4500.0, 36.0));
        consumoEnergiaRepository.save(new ConsumoEnergia(
                "Laboratorio Cientifico", "nuclear", 8200.0, 65.6));
        consumoEnergiaRepository.save(new ConsumoEnergia(
                "Sistema de Suporte a Vida", "nuclear", 6000.0, 144.0));
        consumoEnergiaRepository.save(new ConsumoEnergia(
                "Iluminacao de Emergencia", "bateria", 1200.0, 9.6));
    }

    private void carregarClimatizacoes() {
        climatizacaoRepository.save(new Climatizacao(
                "Modulo de Habitacao A", 22.0, 22.4, 45.0, 101.3, "automatico", true));
        climatizacaoRepository.save(new Climatizacao(
                "Laboratorio Cientifico", 20.0, 20.1, 40.0, 101.0, "automatico", true));
        climatizacaoRepository.save(new Climatizacao(
                "Estufa Hidroponica", 24.0, 25.8, 70.0, 100.5, "manual", true));
    }

    private void carregarAlertas() {
        alertaRepository.save(new AlertaOperacional(
                "Temperatura acima do alvo na Estufa",
                "A estufa hidroponica registra 25.8C, acima do alvo de 24.0C.",
                "medio", "Climatizacao - Estufa Hidroponica", false));
        alertaRepository.save(new AlertaOperacional(
                "Nivel de combustivel baixo",
                "Tanque de hidrazina em 31% da capacidade. Planejar reabastecimento.",
                "alto", "Reservatorio - Plataforma de Pouso", false));
        alertaRepository.save(new AlertaOperacional(
                "Verificacao de rotina concluida",
                "Inspecao diaria dos sensores de oxigenio concluida sem anomalias.",
                "baixo", "Sensor - Modulo de Habitacao A", true));
    }
}
