package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    private Connection connection = null;
    private PreparedStatement ps = null;
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `user` (" +
                "`id` int (10) AUTO_INCREMENT,\n" +
                "`name` varchar(45) NOT NULL,\n" +
                "`lastName` varchar(45) NOT NULL,\n" +
                "`age` int (10) NOT NULL,\n" +
                "PRIMARY KEY (id)\n" +
                ")";
        try {
            connection = Util.getConnection();
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            ps.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

 
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS `user`";
        try {
            connection = Util.getConnection();
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            ps.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

  
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO `user` (`name`,`lastname`, `age`) VALUES (?,?,?)";
        try {
            connection = Util.getConnection();
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    
    public void removeUserById(long id) {
        String sql = "DELETE FROM `user` WHERE `id`= ?";
        try {
            connection = Util.getConnection();
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            ps.setLong(1, id);
            ps.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    
    public List<User> getAllUsers() {
            List<User> allUsers = new ArrayList<>();
            String sql = "SELECT * FROM `user`";
            try {
                connection = Util.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));

                    allUsers.add(user);
                    connection.commit();
                    connection.close();
                }
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                e.printStackTrace();
            }
            return allUsers;
    }
    
        public void cleanUsersTable() {
        String sql = "TRUNCATE `user`";
        try {
            connection = Util.getConnection();
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            ps.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}





















































// public class UserDaoJDBCImpl implements UserDao {
//     private Connection connection = Util.getConnectBD();

//     public UserDaoJDBCImpl() {
//     }

//     public void createUsersTable() {
//         try (Statement statement = connection.createStatement()) {
//             statement.execute("CREATE TABLE IF NOT EXISTS `jm_base`.`users` (\n" +
//                     "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
//                     "  `name` VARCHAR(45) NOT NULL,\n" +
//                     "  `last_name` VARCHAR(45) NOT NULL,\n" +
//                     "  `age` INT(3) NOT NULL,\n" +
//                     "  PRIMARY KEY (`id`))\n" +
//                     "ENGINE = InnoDB\n" +
//                     "DEFAULT CHARACTER SET = utf8;");
// //            System.out.println("Таблица создана");
//         } catch (SQLException e) {
//             e.printStackTrace();
//             try {
//                 connection.rollback();
//             } catch (SQLException throwables) {
//                 throwables.printStackTrace();
//             }
//             e.printStackTrace();
//         }
//     }

//     public void dropUsersTable() {
//         try (Statement statement = connection.createStatement()) {
//             statement.executeUpdate("DROP TABLE IF EXISTS  jm_base.users");
// //            System.out.println("Таблица удалена");
//         } catch (SQLException throwables) {
//             throwables.printStackTrace();
//         }
//     }

//     public void saveUser(String name, String lastName, byte age) {
//         try (PreparedStatement statement = connection.prepareStatement(
//                 "insert into users (name, last_name, age) values (?,?,?)")) {
//             statement.setString(1, name);
//             statement.setString(2, lastName);
//             statement.setByte(3, age);
//             statement.executeUpdate();
// //            System.out.println("Пользователь добавлен");
//         } catch (SQLException throwables) {
//             throwables.printStackTrace();
//         }
//     }

//     public void removeUserById(long id) {
//         try (Statement statement = connection.createStatement()) {
//             statement.executeUpdate("DELETE FROM jm_base.users WHERE `id`= " + id);
// //            System.out.println("Пользователь удален");
//         } catch (SQLException throwables) {
//             throwables.printStackTrace();
//         }
//     }

//     public List<User> getAllUsers() {
//         List<User> users = new ArrayList<>();
//         try (Statement statement = connection.createStatement();
//              ResultSet resultSet = statement.executeQuery("SELECT * FROM jm_base.users")) {
//             String name = null;
//             String lastName = null;
//             Byte age = null;
//             while (resultSet.next()) {
//                 name = resultSet.getString(2);
//                 lastName = resultSet.getString(3);
//                 age = resultSet.getByte(4);
//                 users.add(new User(name, lastName, age));
//             }
//         } catch (SQLException throwables) {
//             throwables.printStackTrace();
//         }
//         return users;
//     }

//     public void cleanUsersTable() {
//         try (Statement statement = connection.createStatement()) {
//             statement.executeUpdate("DELETE FROM jm_base.users");
// //            System.out.println("Таблица очищена");
//         } catch (SQLException throwables) {
//             throwables.printStackTrace();
//         }
//     }
// }
