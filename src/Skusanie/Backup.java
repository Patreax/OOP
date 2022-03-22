package Skusanie;

public class Backup {
//    public void logIn() throws IOException {
//        checkData(userNameField.getText(), userPasswordField.getText());
//        //mainController.currentUser = new User(userNameField.getText(), userPasswordField.getText());
//    }
//
//    public void register() throws IOException{
//        if(checkDuplicates(userNameField.getText())){
//            User newUser = new User(userNameField.getText(), userPasswordField.getText());
//            database.storeData(newUser.getUserId(), userNameField.getText(), userPasswordField.getText());
//            database.registeredUsers.add(newUser);
//            errorMessage.setText("User has been registered");
//        }
//
//    }
//
//    private void checkData(String userName, String userPassWord) throws IOException{
//        //File userData = database.userData;
//        try{
//            Scanner reader = new Scanner(userData);
//            while (reader.hasNextLine()) {
//                String[] data = reader.nextLine().split(" ");
//                String name = data[1];
//                String passWord = data[2];
//
//                passWord.replaceAll("\n", "");
//                if(userName.equals(name) && userPassWord.equals(passWord)){
//                    //System.out.println("User with given name has been found");
//                    // Load new Scene
//                    Main main = new Main();
//                    MainScreenController controller = new MainScreenController();
//                    controller.currentUser = new User("Test", "Test");
//
//                    main.changeScene("/GUI/MainScreen.fxml");
////                    controller.displayData();
////                    changeScene("MainScreen.fxml");
//                }
//            }
//            errorMessage.setText("Name or password is incorrect");
//            reader.close();
//        } catch (IOException e){
//            System.out.println("Error has occurred");
//        }
//
//    }
//
//    public boolean checkDuplicates(String userName) throws IOException{
//        //File userData = database.userData;
//        Scanner reader = new Scanner(userData);
//        while (reader.hasNextLine()){
//            String[] data = reader.nextLine().split(" ");
//            String name = data[1];
//            if(userName.equals(name)){
//                errorMessage.setText("Name is already taken");
//                return false;
//            }
//        }
//        return true;
//    }






//    public ArrayList<User> registeredUsers = new ArrayList<User>();
//    public ArrayList<User> activeUsers = new ArrayList<User>();
//    public File userData = new File("src/Project/Files/userData.txt");
//
//    public void storeData(long userId, String userName, String passWord){
//        try {
//
//            userName.replaceAll("\\s","");
//            passWord.replaceAll("\\s","");
//            BufferedWriter writer = new BufferedWriter(new FileWriter(userData, true));
//            writer.append(userId + " " + userName + " " + passWord + "\n");
//            writer.close();
//            System.out.println("Data has been stored");
//        }catch (IOException e){
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }

}
