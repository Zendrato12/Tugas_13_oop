import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mahasiswa {
    String sql;
    String url = "jdbc:mysql://localhost:3306/kuliah";
    String user = "root";
    String pass = "";
    Connection koneksi;

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, pass);
    }

    public void readMahasiswa(){
        sql = "select * from mahasiswa ";
        try {
            koneksi = getConnection();
            Statement st = koneksi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.print(rs.getString(1)+"|");
                System.out.print(rs.getString(2)+"|");
                System.out.print(rs.getString(3)+"|");
                System.out.print(rs.getString(4)+"|\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMahasiswa(String nim, String nama, String alamat, String gender){
        sql = "insert into mahasiswa "; 
        sql += "(nim,nama,alamat,gender)";
        sql += " values (?,?,?,?)";
        boolean validasi = true;
        if(nim.length() < 11){
            System.out.println("NIM harus 11 Digit");
            validasi = false;
        }
        if(nama.length() < 2){
            System.out.println("Nama minimal 2Digit");
            validasi= false;
        }

   if (!gender.equals("L") && !gender.equals("P")) {
    System.out.println("Gender L/P");
    validasi = false;
}

        if(validasi==true){
            try {
                koneksi = getConnection();
                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, nim);
                ps.setString(2, nama);
                ps.setString(3, alamat);
                ps.setString(4, gender);

                int result = ps.executeUpdate();
                if(result >0){
                    System.out.println("Data berhasil ditambahkan");
                }
                else{
                    System.out.println("Data Gagal ditambahkan");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean findMahasiswaByNim(String nim){
        sql = "select nama from mahasiswa where nim=" + nim;
        try {
            koneksi = getConnection();
            Statement st = koneksi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    public void updateMahasiswa(String nama, String alamat, String gender, String nim){
        sql = 
        "update mahasiswa set nama=?,alamat=?,gender=? where nim=?";
        try {
            koneksi = getConnection();
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, gender);
            ps.setString(4, nim);
            int result = ps.executeUpdate();
            if(result > 0){
                System.out.println("Data berhasil diubah");
            }
            else{
                System.out.println("Data gagal diubah");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteMahasiswa(String nim){
        sql = "delete from mahasiswa where nim=?";
        try {
            koneksi = getConnection();
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, nim);
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println("Data berhasil dihapus");
            }
            else{
                System.out.println("Data gagal dihapus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
