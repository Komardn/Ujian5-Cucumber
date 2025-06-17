Feature: Keranjang Belanja - Tambah Produk ke Keranjang

  Scenario: Tambah produk ke keranjang belanja dan verifikasi tampilannya
    Given Login dengan user valid untuk tambah produk ke keranjang
    When Tambah produk ke keranjang dan buka halaman keranjang
    Then Produk muncul di halaman keranjang
