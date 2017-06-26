/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.pagerank;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gachanja
 */
public class PageRank {

    public static void main(String[] args) {
        Statement stmt = null;
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/news", "news", "news");
            stmt = connection.createStatement();
            String sql = "update articles set pagerank = 1000";
            stmt.executeUpdate(sql);

            sql = "select p.articleid , count(*) from articles a join pageviews p on a.articleid = p.articleid group by p.articleid order by count(*) desc;";
            ResultSet pageRank = stmt.executeQuery(sql);
            int rank = 1;
            while (pageRank.next()) {
                String selectSQL = "update articles set pagerank = ? where articleid = ?";
                PreparedStatement statement = connection.prepareStatement(selectSQL);
                statement.setInt(1, rank);
                statement.setInt(2, pageRank.getInt("articleid"));
                statement.executeUpdate();

                System.out.println(pageRank.getInt("articleid"));
                rank++;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e.getMessage());
            return;
        }
    }

}
