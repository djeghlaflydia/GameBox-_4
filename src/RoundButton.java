import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class RoundButton extends JButton {

    private Color shadowColor = new Color(0, 0, 0, 80); // Couleur de l'ombre
    private Color baseColor = new Color(153, 31, 0, 150); // Couleur de base
    private Color hoverColor = new Color(153, 31, 0, 200); // Couleur lorsqu'on survole le bouton
    private Color clickedColor = new Color(153, 31, 0, 100); // Couleur lorsqu'on clique sur le bouton
    private boolean isHovered = false;
    private boolean isClicked = false;

    public RoundButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(new Font("MV Boli", Font.BOLD, 23));

        // Ajouter les écouteurs de souris pour détecter le survol et le clic
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isClicked = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isClicked = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        int radius = 20; // Rayon des coins arrondis

        // Dessiner l'ombre
        g2d.setColor(shadowColor);
        g2d.fill(new RoundRectangle2D.Double(5, 5, width - 10, height - 10, radius, radius));

        // Dessiner le bouton arrondi avec couleur changeante
        g2d.setColor(isClicked ? clickedColor : (isHovered ? hoverColor : baseColor));
        g2d.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, radius, radius));

        // Dessiner le texte
        g2d.setColor(getForeground());
        FontMetrics fm = g2d.getFontMetrics();
        int x = (width - fm.stringWidth(getText())) / 2;
        int y = (height + fm.getAscent() - fm.getDescent()) / 2;
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }
}
