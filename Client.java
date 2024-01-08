import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.*;

import Airline.AdminService;
import Airline.AuthService;
import Airline.AuthServiceHelper;
import Airline.ClientService;

import javax.naming.Context;
import javax.naming.InitialContext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Airline.*;

public class Client extends JFrame {
       private JTextField usernameField;
       private JPasswordField passwordField;

       public Client() {
              super("Welcome to Dumb & Dumber Airlines!");

              usernameField = new JTextField(20);
              passwordField = new JPasswordField(20);
              JButton loginButton = new JButton("Login");

              loginButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            String username = usernameField.getText();
                            char[] passwordChars = passwordField.getPassword();
                            String password = new String(passwordChars);

                            try {
                                   Context ctx = new InitialContext();
                                   Object ref = ctx.lookup("AuthService");
                                   AuthService sm = AuthServiceHelper.narrow((org.omg.CORBA.Object) ref);

                                   if (sm != null) {
                                          if (sm.Authothification(username, password)) {
                                                 openMenu(username, password);
                                          } else {
                                                 JOptionPane.showMessageDialog(Client.this,
                                                               "Username or password is incorrect", "Error",
                                                               JOptionPane.ERROR_MESSAGE);
                                          }
                                   } else {
                                          JOptionPane.showMessageDialog(Client.this, "Failed to connect to the server",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                   }
                            } catch (Exception ex) {
                                   ex.printStackTrace();
                            }
                     }
              });

              JPanel panel = new JPanel();
              panel.add(new JLabel("Username: "));
              panel.add(usernameField);
              panel.add(new JLabel("Password: "));
              panel.add(passwordField);
              panel.add(loginButton);

              add(panel);
              setSize(600, 150);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              setLocationRelativeTo(null);
              setVisible(true);
       }

       private void openMenu(String username, String password) {
              if ("admin".equals(username) || "admin".equals(password)) {
                     AdminMenu adminMenu = new AdminMenu();
                     adminMenu.setVisible(true);
              } else if ("client".equals(username) || "client".equals(password)) {
                     ClientMenu clientMenu = new ClientMenu();
                     clientMenu.setVisible(true);
              } else {
                     JOptionPane.showMessageDialog(this, "Username or password is incorrect", "Error",
                                   JOptionPane.ERROR_MESSAGE);
              }
              this.dispose();
       }

       public static void main(String[] args) {
              SwingUtilities.invokeLater(new Runnable() {
                     @Override
                     public void run() {
                            new Client();
                     }
              });
       }
}

class AdminMenu extends JFrame {
       public AdminMenu() {
              super("Dumb & Dumber Admin Menu");

              JButton addFlightButton = new JButton("Add New Flight");
              JButton addAirportButton = new JButton("Add New Airport");
              JButton registerPassengerButton = new JButton("Register New Passenger");
              JButton viewAllFlightsButton = new JButton("View All Flights");
              JButton editFlight = new JButton("Edit Flight");
              JButton editAirport = new JButton("Edit Airport");

              addFlightButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            NewFlight();
                     }
              });
              addAirportButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            NewAirport();
                     }
              });

              registerPassengerButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            registerPassenger();
                     }
              });
              viewAllFlightsButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            viewAllFlights();
                     }
              });

              editFlight.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            editFlight();
                     }
              });
              editAirport.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            editAirport();
                     }
              });

              JPanel panel = new JPanel();
              panel.add(addFlightButton);
              panel.add(addAirportButton);
              panel.add(registerPassengerButton);
              panel.add(viewAllFlightsButton);
              panel.add((editFlight));
              panel.add((editAirport));

              add(panel);
              setSize(600, 150);
              setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
              setLocationRelativeTo(null);
       }

       public void NewFlight() {
              String code = JOptionPane.showInputDialog(this, "Enter Flight Code:");
              String departureAirport = JOptionPane.showInputDialog(this, "Enter Departure Airport:");
              String arrivalAirport = JOptionPane.showInputDialog(this, "Enter Arrival Airport:");
              String departureTime = JOptionPane.showInputDialog(this, "Enter Departure Time:");
              String arrivalTime = JOptionPane.showInputDialog(this, "Enter Arrival Time:");
              double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Price:"));

              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("AdminService");

                     AdminService sm = AdminServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.addNewFlight(code, departureAirport, arrivalAirport, departureTime, arrivalTime, price);
                     } else {
                            JOptionPane.showMessageDialog(this, "Failed to connect to the server", "Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }

       public void NewAirport() {
              String code = JOptionPane.showInputDialog(this, "Enter Airport Code:");
              String city = JOptionPane.showInputDialog(this, "Enter City:");
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("AdminService");

                     AdminService sm = AdminServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.addNewAirport(code, city);
                     } else {
                            JOptionPane.showMessageDialog(this, "Failed to connect to the server", "Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }

       public void registerPassenger() {
              String passengerName = JOptionPane.showInputDialog(this, "Enter Your Full Name:");
              String phonenumber = JOptionPane.showInputDialog(this, "Enter Your Phone Number:");
              String address = JOptionPane.showInputDialog(this, "Enter Your Address:");
              String email = JOptionPane.showInputDialog(this, "Enter Your Email:");
              String passport = JOptionPane.showInputDialog(this, "Enter Your Passport ID:");
              String seattype = JOptionPane.showInputDialog(this, "Choose Your Flight Type(First/Economy) Class:");
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("AdminService");

                     AdminService sm = AdminServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.registerNewPassenger(passengerName, phonenumber, address, email, passport, seattype);
                     } else {
                            JOptionPane.showMessageDialog(this, "Failed to connect to the server", "Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }

       public void viewAllFlights() {
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("AdminService");

                     AdminService sm = AdminServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.viewAllFlights();
                     } else {
                            JOptionPane.showMessageDialog(this, "There are no flights", "No Flights Found",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }

       public void editFlight() {
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("AdminService");

                     AdminService sm = AdminServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.editFlight();
                     } else {
                            JOptionPane.showMessageDialog(this, "Failed to connect to the server", "Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }

       public void editAirport() {
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("AdminService");

                     AdminService sm = AdminServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.editAirport();
                     } else {
                            JOptionPane.showMessageDialog(this, "Failed to connect to the server", "Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }
}

class ClientMenu extends JFrame {
       public ClientMenu() {
              super("Dumb & Dumber Client Menu");

              JButton viewAllFlightsButton = new JButton("View All Flights");
              JButton availableseatsButton = new JButton("Available Seats");
              JButton registerPassengerButton = new JButton("Book A Seat");
              JButton paymentButton = new JButton("Payment");

              viewAllFlightsButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            viewAllFlights();
                     }
              });

              registerPassengerButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            NewregisterPassenger();
                     }
              });

              availableseatsButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            availableseats();
                     }
              });

              paymentButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                            payment();
                     }
              });

              JPanel panel = new JPanel();
              panel.add(viewAllFlightsButton);
              panel.add(registerPassengerButton);
              panel.add(availableseatsButton);
              panel.add(paymentButton);

              add(panel);
              setSize(600, 150);
              setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
              setLocationRelativeTo(null);
       }

       public void NewregisterPassenger() {
              String passengerName = JOptionPane.showInputDialog(this, "Enter Your Full Name:");
              String phonenumber = JOptionPane.showInputDialog(this, "Enter Your Phone Number:");
              String address = JOptionPane.showInputDialog(this, "Enter Your Address:");
              String email = JOptionPane.showInputDialog(this, "Enter Your Email:");
              String passport = JOptionPane.showInputDialog(this, "Enter Your Passport ID:");
              String seattype = JOptionPane.showInputDialog(this, "Choose Your Flight Type(First/Economy) Class:");
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("ClientService");

                     ClientService sm = ClientServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.registerNewPassenger(passengerName, phonenumber, address, email, passport, seattype);
                     } else {
                            JOptionPane.showMessageDialog(this, "Failed to connect to the server", "Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }

       public void viewAllFlights() {
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("ClientService");

                     ClientService sm = ClientServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.viewAllFlights();
                     } else {
                            JOptionPane.showMessageDialog(this, "There are no flights", "No Flights Found",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }

       public void availableseats() {
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("ClientService");

                     ClientService sm = ClientServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.availableseats();
                     } else {
                            JOptionPane.showMessageDialog(this, "Failed to connect to the server", "Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }

       public void payment() {
              double payment = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Payment:"));
              try {
                     Context ctx = new InitialContext();
                     Object ref = ctx.lookup("ClientService");

                     ClientService sm = ClientServiceHelper.narrow((org.omg.CORBA.Object) ref);
                     if (sm != null) {
                            sm.payment(payment);
                            JOptionPane.showMessageDialog(this, "Payment Successful", "Payment",
                                          JOptionPane.INFORMATION_MESSAGE);
                     } else {
                            JOptionPane.showMessageDialog(this, "Failed to connect to the server", "Error",
                                          JOptionPane.ERROR_MESSAGE);
                     }
              } catch (Exception ex) {
                     ex.printStackTrace();
              }
       }
}
