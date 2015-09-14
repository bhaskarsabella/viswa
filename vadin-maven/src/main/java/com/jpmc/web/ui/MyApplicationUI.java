package com.jpmc.web.ui;


import java.util.HashMap;
import java.util.Locale;






import com.jpmc.dao.LoginDao;
import com.jpmc.dao.LoginDaoImpl;
import com.jpmc.model.Login;
import com.jpmc.service.LoginService;
import com.jpmc.service.LoginServiceImpl;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public class MyApplicationUI extends UI {
	
	VerticalLayout loginLayout;
	 CssLayout root = new CssLayout();
	  private Navigator nav;
	  CssLayout menu = new CssLayout();
	    CssLayout content = new CssLayout();
	    HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>() {
	        {
	            /*put("/dashboard", DashboardView.class);
	            put("/sales", SalesView.class);
	            put("/transactions", TransactionsView.class);
	            put("/reports", ReportsView.class);
	            put("/schedule", ScheduleView.class);*/
	        }
	    };

    @Override
    protected void init(VaadinRequest request) {
       /* VerticalLayout view = new VerticalLayout();
        view.addComponent(new Label("Hello Vaadin!"));
        setContent(view);*/
        
        setLocale(Locale.US);

        setContent(root);
        root.addStyleName("root");
        root.setSizeFull();

        // Unfortunate to use an actual widget here, but since CSS generated
        // elements can't be transitioned yet, we must
        Label bg = new Label();
        bg.setSizeUndefined();
        bg.addStyleName("login-bg");
        root.addComponent(bg);

        buildLoginView(false);
    }
    
    private void buildLoginView(boolean exit) {
        if (exit) {
            root.removeAllComponents();
        }
       
       

        addStyleName("login");

        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
        loginLayout.addStyleName("login-layout");
        root.addComponent(loginLayout);

        final CssLayout loginPanel = new CssLayout();
        loginPanel.addStyleName("login-panel");

        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        loginPanel.addComponent(labels);

        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);

      

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        final TextField username = new TextField("Username");
        username.focus();
        fields.addComponent(username);

        final PasswordField password = new PasswordField("Password");
        fields.addComponent(password);

        final Button signin = new Button("Sign In");
        signin.addStyleName("default");
        fields.addComponent(signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        final ShortcutListener enter = new ShortcutListener("Sign In",
                KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                signin.click();
            }
        };

        signin.addClickListener(new ClickListener() {
           // @Override
            public void buttonClick(ClickEvent event) {
            	
            	
            	
            	

                if (username.getValue() != null
                        && username.getValue().equals("")
                        && password.getValue() != null
                        && password.getValue().equals("")) {
                    signin.removeShortcutListener(enter);
                    
                    Label error = new Label(
                            "<span style='color:red;'> Please Enter userid and Password </span> ",
                            ContentMode.HTML);
                    error.addStyleName("error");
                    error.setSizeUndefined();
                    error.addStyleName("light");
                    // Add animation
                    error.addStyleName("v-animate-reveal");
                    loginPanel.addComponent(error);
                    username.focus();
                    return;
                   
                }
                
                else{
                	
                	LoginService dao = new LoginServiceImpl();
                Login login =  	dao.getLoginDetails(username.getValue(), password.getValue());
                
                
                	
                	 if (loginPanel.getComponentCount() > 2) {
                         // Remove the previous error message
                         loginPanel.removeComponent(loginPanel.getComponent(2));
                     }
			                if(login == null){
			                     Label error = new Label(
			                             "<span style='color:red;'> Wrong username or password. </span> ",
			                             ContentMode.HTML);
			                     error.addStyleName("error");
			                     error.setSizeUndefined();
			                     error.addStyleName("light");
			                     // Add animation
			                     error.addStyleName("v-animate-reveal");
			                     loginPanel.addComponent(error);
			                     username.focus();
			                     
			                }
			                
			                else {
			                	   buildMainView();	
			                }
                	
                }
            	
            	
            	
               
                
            }
        });

        signin.addShortcutListener(enter);

        loginPanel.addComponent(fields);

        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }
    
    private void buildMainView() {

        nav = new Navigator(this, content);

        for (String route : routes.keySet()) {
            nav.addView(route, routes.get(route));
        }

        //helpManager.closeAll();
        removeStyleName("login");
        root.removeComponent(loginLayout);

        root.addComponent(new HorizontalLayout() {
            {
                setSizeFull();
                addStyleName("main-view");
                addComponent(new VerticalLayout() {
                    // Sidebar
                    {
                        addStyleName("sidebar");
                        setWidth(null);
                        setHeight("100%");

                        // Branding element
                        addComponent(new CssLayout() {
                            {
                                addStyleName("branding");
                                Label logo = new Label(
                                        "<span>Welcome to JSPM</span>.",
                                        ContentMode.HTML);
                                logo.setSizeUndefined();
                                addComponent(logo);
                                // addComponent(new Image(null, new
                                // ThemeResource(
                                // "img/branding.png")));
                            }
                        });

                        // Main menu
                        addComponent(menu);
                        setExpandRatio(menu, 1);

                        // User menu
                        addComponent(new VerticalLayout() {
                            {
                                setSizeUndefined();
                                addStyleName("user");
                                Image profilePic = new Image(
                                        null,
                                        new ThemeResource("img/profile-pic.png"));
                                profilePic.setWidth("34px");
                               // addComponent(profilePic);
                                /*Label userName = new Label(Generator
                                        .randomFirstName()
                                        + " "
                                        + Generator.randomLastName());
                                userName.setSizeUndefined();
                                addComponent(userName);*/

                                Command cmd = new Command() {
                                 //   @Override
                                    public void menuSelected(
                                            MenuItem selectedItem) {
                                        Notification
                                                .show("Not implemented in this demo");
                                    }
                                };
                                MenuBar settings = new MenuBar();
                                MenuItem settingsMenu = settings.addItem("",
                                        null);
                                settingsMenu.setStyleName("icon-cog");
                                settingsMenu.addItem("Settings", cmd);
                                settingsMenu.addItem("Preferences", cmd);
                                settingsMenu.addSeparator();
                                settingsMenu.addItem("My Account", cmd);
                                addComponent(settings);

                                Button exit = new NativeButton("Exit");
                                exit.addStyleName("icon-cancel");
                                exit.setDescription("Sign Out");
                                addComponent(exit);
                                exit.addClickListener(new ClickListener() {
                                  //  @Override
                                    public void buttonClick(ClickEvent event) {
                                        buildLoginView(true);
                                    }
                                });
                            }
                        });
                    }
                });
                // Content
                addComponent(content);
                content.setSizeFull();
                content.addStyleName("view-content");
                setExpandRatio(content, 1);
            }

        });
    }

}