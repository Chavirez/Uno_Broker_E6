/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.mvc;

import itson.dominio.Carta;
import itson.dominio.Jugador;
import itson.dominio.Partida;
import itson.dominio.observador.ObservadorPartida;

import javax.swing.*;
import java.awt.*;

public class VistaMesaJuego extends JFrame implements ObservadorPartida {

    private ControladorPartida controlador;
    private String miIdJugador; // El ID del jugador que está usando esta ventana

    private JLayeredPane panelMesa;
    private JPanel panelMano;
    private JLabel lblEstadoTurno;

    public VistaMesaJuego(ControladorPartida controlador, String miIdJugador) {
        this.controlador = controlador;
        this.miIdJugador = miIdJugador;

        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("UNO - Jugador: " + miIdJugador);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Centrar en pantalla
    }

    private void inicializarComponentes() {
        // 1. Panel superior para el estado
        lblEstadoTurno = new JLabel("Esperando estado del juego...", SwingConstants.CENTER);
        lblEstadoTurno.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblEstadoTurno, BorderLayout.NORTH);

        // 2. El centro de la mesa (Mazo y Descarte) usando JLayeredPane
        panelMesa = new JLayeredPane();
        panelMesa.setBackground(new Color(34, 139, 34)); // Verde mesa de casino
        panelMesa.setOpaque(true);
        add(panelMesa, BorderLayout.CENTER);

        // 3. Panel inferior para la mano del jugador
        panelMano = new JPanel();
        panelMano.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Cartas en fila
        panelMano.setBackground(Color.DARK_GRAY);
        add(panelMano, BorderLayout.SOUTH);
    }

    @Override
    public void actualizarEstado(Partida partida) {
        // --- 1. ACTUALIZAR TEXTOS ---
        Jugador turnoActual = partida.obtenerJugadorActual();
        if (turnoActual.getId().equals(miIdJugador)) {
            lblEstadoTurno.setText("¡Es tu turno! Color actual: " + partida.getColorActual());
        } else {
            lblEstadoTurno.setText("Turno de: " + turnoActual.getNombre());
        }

        // --- 2. DIBUJAR LA MESA (JLayeredPane) ---
        panelMesa.removeAll(); // Limpiamos la mesa antes de redibujar

        // Dibujar carta en la cima del descarte
        Carta cima = partida.getPilaDescarte().obtenerCima();
        if (cima != null) {
            JLabel lblCima = crearEtiquetaCarta(cima.getId(), cima.getColor().name());
            
            // ¡CRÍTICO EN JLAYEREDPANE! Si no pones setBounds, el JLabel será invisible.
            // Lo centramos manualmente en el panel (suponiendo tamaño 800x600)
            lblCima.setBounds(350, 150, 100, 150); 
            
            // Lo añadimos a la capa base
            panelMesa.add(lblCima, Integer.valueOf(0)); 
        }

        // --- 3. DIBUJAR LA MANO ---
        panelMano.removeAll();
        Jugador yo = null;
        for (Jugador j : partida.getJugadores()) { // Asume que agregaste getJugadores() en Partida
            if (j.getId().equals(miIdJugador)) {
                yo = j;
                break;
            }
        }

        if (yo != null) {
            for (int i = 0; i < yo.getMano().cantidadCartas(); i++) {
                Carta c = yo.getMano().obtenerCarta(i);
                JLabel lblCartaMano = crearEtiquetaCarta(c.getId(), c.getColor().name());
                // Aquí usamos JPanel con FlowLayout, así que no hace falta setBounds
                panelMano.add(lblCartaMano); 
            }
        }

        // Refrescar la interfaz para que los cambios se vean
        revalidate();
        repaint();
    }

    // Método auxiliar para crear la representación visual de una carta
    private JLabel crearEtiquetaCarta(String id, String colorStr) {
        JLabel lbl = new JLabel("<html><center>" + id + "<br>" + colorStr + "</center></html>", SwingConstants.CENTER);
        lbl.setOpaque(true);
        lbl.setBackground(Color.WHITE);
        lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lbl.setPreferredSize(new Dimension(80, 120)); // Tamaño para FlowLayout
        return lbl;
    }
}