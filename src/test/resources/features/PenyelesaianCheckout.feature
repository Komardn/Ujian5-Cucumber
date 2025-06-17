Feature: Penyelesaian Checkout

  Scenario: Pengguna menyelesaikan proses checkout dan melihat pesan konfirmasi
    Given Pengguna login dan berada di halaman ringkasan pembelian
    When Pengguna klik tombol Finish untuk menyelesaikan pembelian
    Then Muncul pesan THANK YOU FOR YOUR ORDER
    When Pengguna klik ikon menu dan pilih Logout
    Then Pengguna berhasil logout dan berada di halaman login
