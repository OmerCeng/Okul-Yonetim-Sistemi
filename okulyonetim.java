import java.io.*;
import java.rmi.StubNotFoundException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        okulYonetim yonetim = new okulYonetim();  // okulyonetim classının kurucu metodu

        int secim6; 
     
        do {
            System.out.println("1. Okul dizisini dosyaya yazdir");
            System.out.println("2. Okul dizisini dosyadan oku");
            System.out.println("3. Yeni okul ekle");
            System.out.println("4. Okul adi verilerek bir okulun bilgilerini bul ve goster");
            System.out.println("5. Tüm okullarin bilgilerini göster");
            System.out.println("6. Ayni şehirdeki okullari göster");
            System.out.println("7. Okul kaydini değiştir");
            System.out.println("8. Okul adini kullanarak bir okul kaydini sil");
            System.out.println("9. Programi kapat");
            System.out.print("Lütfen seçiminizi yapin: ");
            secim6 = scanner.nextInt();

             switch (secim6) {
                case 1:
                   yonetim.dosyayaYazdir();
                    break;
                case 2:
                   yonetim.dosyadanOku();
                    break;
                case 3:
                    yonetim.okulEkle(scanner);
                    break;
                case 4:
                    yonetim.okuladiBilgisiGoster(scanner);
                    break;
                case 5:
                   yonetim.tümOkullarigöster(scanner);
                    break;
                case 6:
                    yonetim.ayniSehirdekilerigöster(scanner);
                    break;
                case 7:
                    yonetim.okulKaydiniDegistir(scanner);
                    break;
                case 8:
                    yonetim.okulKaydiniSil(scanner);
                    break;
                case 9:
                    System.out.println("Programdan cikiliyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen 1 ile 10 arasinda bir sayi girin.");
            }
        } while (secim6 != 9);
        

    }
}

class Okul {
    
    private String adi;
    private String sehir;
    private String ulke;
    private String telefonNumarasi;
    private int ogrenciSayisi;
    private int kurulusYili;

    // Kurucu metot
    public Okul(String adi, String sehir, String ulke, String telefonNumarasi, int ogrenciSayisi, int kurulusYili) {
        this.adi = adi;
        this.sehir = sehir;
        this.ulke = ulke;
        this.telefonNumarasi = telefonNumarasi;
        this.ogrenciSayisi = ogrenciSayisi;
        this.kurulusYili = kurulusYili;
    }

    // Okuyucu ve yazici metotlar
    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getUlke() {
        return ulke;
    }

    public void setUlke(String ulke) {
        this.ulke = ulke;
    }

    public String getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(String telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    public int getOgrenciSayisi() {
        return ogrenciSayisi;
    }

    public void setOgrenciSayisi(int ogrenciSayisi) {
        this.ogrenciSayisi = ogrenciSayisi;
    }

    public int getKurulusYili() {
        return kurulusYili;
    }

    public void setKurulusYili(int kurulusYili) {
        this.kurulusYili = kurulusYili;
    }

    // Okul bilgilerini gosteren metot
    public void bilgileriGoster() {
        System.out.println("Okul Adi: " + adi);
        System.out.println("Konum: " + sehir + ", " + ulke);
        System.out.println("Telefon Numarasi: " + telefonNumarasi);
        System.out.println("Öğrenci Sayisi: " + ogrenciSayisi);
        System.out.println("Kuruluş Yili: " + kurulusYili);
        System.out.println();
    }
}

 class okulYonetim{


   private static int maxokul = 100;
    private static Okul[] okullar = new Okul[maxokul];
    private static int okulSayisi = 0;
    Scanner scanner = new Scanner(System.in);

        public void  okulEkle(Scanner scanner) {
            if (okulSayisi < maxokul) {
                scanner.nextLine().trim(); 
                System.out.println("Adini giriniz: "); 
                String adi = scanner.nextLine(); // tüm satırı input olarak alır
                System.out.println("Sehir giriniz: ");
                String sehir = scanner.next(); 
                System.out.println("Ulke giriniz: ");
                String ulke = scanner.next();
                System.out.println("telefon numaraasi giriniz: ");
                String telefonNumarasi = scanner.next();
                System.out.println("Ogrenci sayisi giriniz: ");
                int ogrenciSayisi = scanner.nextInt();
                System.out.println("Kurulus yilini giriniz: ");
                int kurulusYili = scanner.nextInt();
                okullar[okulSayisi] = new Okul(adi, sehir, ulke, telefonNumarasi, ogrenciSayisi, kurulusYili);
                okulSayisi++;
                System.out.println("Okul başariyla eklendi.");
            } else {
                System.out.println("......max okul sayisinin üstüne ciktiniz!!.........");
            }
            scanner.nextLine();
        }
         public void okuladiBilgisiGoster(Scanner scanner) {
            scanner.nextLine().trim(); 
            System.out.println("Bilgisini gormek istediginiz okulun adini giriniz: ");
            String gösterilcek = scanner.nextLine();
            System.out.println("görmek istediginiz okul: " + gösterilcek);
            boolean okulBulundu = false;
            for (int i = 0; i < okulSayisi; i++) {
                if (okullar[i] != null && okullar[i].getAdi().equals(gösterilcek)) { // alınan input adi degişkenine eşitse bilgileri gösterir
                    okullar[i].bilgileriGoster(); 
                    okulBulundu = true;
                    break;
                }
            }
            
            if (!okulBulundu) {
                System.out.println("!!!!!!!!!!!!Belirtilen isimde bir okul bulunamadi!!!!!!!!!!!!!!"); 
            }
        }

        public void tümOkullarigöster(Scanner scanner){
            if (okulSayisi == 0) {
                System.out.println("Kayitli okul bulunmamaktadir.");
            } else {
                System.out.println("Tüm Okullar:");
                for (int i = 0; i < okulSayisi; i++) {
                    okullar[i].bilgileriGoster();
                }
            }
        }
        public void ayniSehirdekilerigöster(Scanner scanner) {
            System.out.println("Bilgisini gormek istediginiz okulun sehrini giriniz: ");
            String gösterilcek2 = scanner.next();
            System.out.println("görmek istediginiz okulun sehri: " + gösterilcek2);
            boolean okulBulundu = false;
            for (int i = 0; i < okulSayisi; i++) {
                if (okullar[i].getSehir().equals(gösterilcek2)) { // alınan input sehir degişkenine eşitse bilgileri gösterir
                    okullar[i].bilgileriGoster(); 
                    okulBulundu = true;
                }
            }
            
            if (!okulBulundu) {
                System.out.println("Belirtilen sehirde bir okul bulunamadi.");
            }
        }
        public void okulKaydiniDegistir(Scanner scanner){
            System.out.println("Bilgisi degistirilecek okulun adini giriniz: ");
            String degistircek = scanner.next();
            for( int i = 0; i<okulSayisi; i++){
                if(okullar[i].getSehir().equals(degistircek)){
                    break;
                }
                okullar[i].bilgileriGoster();
                System.out.println("degistirilecek bilgiyi secin: ");
                System.out.println("1) adini degistir");
                System.out.println("2) sehiri degistir");
                System.out.println("3) ulke degistir");
                System.out.println("4) telefon numarasi degistir");
                System.out.println("5) ogrenci sayisi degistir");
                System.out.println("6) kurulus yilini degistir");
                System.out.println("7) cikis");
                
                int temp = scanner.nextInt();
                switch(temp){

                    case 1: 
                    System.out.println("Yeni adi giriniz: ");
                    String temp2 = scanner.next();
                    okullar[i].setAdi(temp2);
                    System.out.println("degistirildi.. yeni adi:  "+ okullar[i].getAdi());
                    break;

                    case 2:
                    System.out.println("yeni sehri giriniz: ");
                    String temp3 = scanner.next();
                    okullar[i].setSehir(temp3);
                    System.out.println("degistirildi... yeni sehir: " + okullar[i].getSehir());
                    break;

                    case 3:
                    System.out.println("yeni ulkeyi giriniz: ");
                    String temp4 = scanner.next();
                    okullar[i].setUlke(temp4);
                    System.out.println("degistirildi... yeni ulke: " + okullar[i].getUlke());
                    break;

                    case 4:
                    System.out.println("yeni telefon numarasini giriniz: ");
                    String temp5 = scanner.next();
                    okullar[i].setTelefonNumarasi(temp5);
                    System.out.println("degistirildi... telefon numarasi: " + okullar[i].getTelefonNumarasi());
                    break;

                    case 5:
                    System.out.println("yeni ogrenci sayisini giriniz: ");
                    int temp6 = scanner.nextInt();
                    okullar[i].setOgrenciSayisi(temp6);
                    System.out.println("degistirildi... yeni ogrenci sayisi: " + okullar[i].getOgrenciSayisi());
                    break;

                    case 6:
                    System.out.println("yeni kurulus yilini giriniz: ");
                    int temp7 = scanner.nextInt();
                    okullar[i].setKurulusYili(temp7);
                    System.out.println("degistirildi... yeni kurulus yili: " + okullar[i].getKurulusYili());
                    case 7:
                    break;
                }
            } 
        }
        
        public void okulKaydiniSil(Scanner scanner){

            System.out.println("Kaydini silmek istediginiz okulun adini giriniz: ");
            String gösterilcek2 = scanner.next();
            System.out.println("silmek istediginiz okul: " + gösterilcek2);
            boolean silindi = false;
            for (int i = 0; i < okulSayisi; i++) {
                if (okullar[i].getAdi().equals(gösterilcek2)) {
                    okullar[i] = null;
                    silindi = true;
                    System.out.println(".........okul basariyla silindi..........");
                    break;
                }
                
            }
            
            if (!silindi) {
                System.out.println("Belirtilen isimde bir okul bulunamadi.");
            }
        }
        
        public void dosyayaYazdir() {
            try {
                FileWriter dosyaYazici = new FileWriter("okul_verileri.txt");
                PrintWriter yazici = new PrintWriter(dosyaYazici);
        
                for (int i = 0; i < okulSayisi; i++) {
                    yazici.println("Okul Adi: " + okullar[i].getAdi());
                    yazici.println("Şehir: " + okullar[i].getSehir());
                    yazici.println("Ülke: " + okullar[i].getUlke());
                    yazici.println("Telefon Numarasi: " + okullar[i].getTelefonNumarasi());
                    yazici.println("Öğrenci Sayisi: " + okullar[i].getOgrenciSayisi());
                    yazici.println("Kuruluş Yili: " + okullar[i].getKurulusYili());
                    yazici.println();
                }
                System.out.println("Okul verileri dosyaya basariyla yazildi.");
                yazici.close();
                dosyaYazici.close(); 
                
            } catch (IOException e) {
                System.out.println("Dosya yazma hatasi: " + e.getMessage());
            }
        }

        public void dosyadanOku() {
            try {
                File dosya = new File("okul_verileri.txt");
                Scanner okuyucu = new Scanner(dosya);
        
                while (okuyucu.hasNextLine()) {
                    String okulAdi = okuyucu.nextLine().substring(10); 
                    String sehir = okuyucu.nextLine().substring(7); 
                    String ulke = okuyucu.nextLine().substring(6); 
                    String telefonNumarasi = okuyucu.nextLine().substring(18); 
                    int ogrenciSayisi = Integer.parseInt(okuyucu.nextLine().substring(16)); 
                    int kurulusYili = Integer.parseInt(okuyucu.nextLine().substring(14)); 
                    okullar[okulSayisi] = new Okul(okulAdi, sehir, ulke, telefonNumarasi, ogrenciSayisi, kurulusYili);
                    okulSayisi++;
                    okuyucu.nextLine(); 
                }
                System.out.println("Okul verileri dosyadan başariyla okundu.");

                okuyucu.close();
            } catch (FileNotFoundException e) {
                System.out.println("Dosya bulunamadi: " + e.getMessage());
            }
        }
        

    

    }







    
   
        

 
 
 

