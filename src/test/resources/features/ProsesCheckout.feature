Feature: Checkout Process - Proceed to checkout

  Scenario: User proceeds to checkout after adding a product to the cart
    Given Produk sudah ditambahkan ke keranjang dan berada di halaman keranjang
    When Pengguna klik tombol Checkout dan mengisi informasi pelanggan
    Then Pengguna diarahkan ke halaman ringkasan pembelian
