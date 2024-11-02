<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="view/css/services.css">
</head>
<body>



<div class="container-content">
    <!-- Left Sidebar (Service Menu) -->
    <div class="sidebar">
        <h3>Services</h3>
        <a href="#" onclick="showServiceInfo('haircut')">Hair Cut</a>
        <a href="#" onclick="showServiceInfo('makeup')">Makeup</a>
        <a href="#" onclick="showServiceInfo('massage')">Massage</a>
        <a href="#" onclick="showServiceInfo('headwashing')" style="padding:15px 0px 15px 0px;">Head Washing</a>
        <a href="#" onclick="showServiceInfo('hairdying')"style="padding:15px 15px;">Hair Dying</a>
        <a href="#" onclick="showServiceInfo('haircurling')"style="padding:15px 0px 15px 10px;">Hair Curling</a>
    </div>

    <!-- Main Content Area -->
    <div class="content">
        <!-- Initial Service Sections -->
        <div id="initial-content">
            <div class="service-section">
                <img src="images/haircut.jpg" alt="Hair Cut">
                <div class="service-description">
                    <h3>Hair Cut</h3>
                    <p>Our professional stylists offer a wide range of hairstyles tailored to complement different face shapes and personal styles. We stay updated with the latest trends to provide you with the best experience. Whether you're looking for a simple trim or a bold new look, our team ensures precision and style that will leave you feeling refreshed and confident.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/makeup.jpg" alt="Makeup">
                <div class="service-description">
                    <h3>Makeup</h3>
                    <p>Transform your look with our professional makeup service. Our makeup artists specialize in creating stunning looks for all occasions, from weddings to evening events. Using high-quality products and techniques, they will enhance your features, giving you a flawless, radiant look that stands out in any setting.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/massage.jpg" alt="Massage">
                <div class="service-description">
                    <h3>Massage</h3>
                    <p>Relax and unwind with our gentle and rejuvenating massage services, designed to relieve stress and tension. Our skilled therapists provide a range of massage techniques, from deep tissue to Swedish, ensuring a tailored experience that helps you recharge and restore balance after a long day.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/headwashing.jpg" alt="Head Washing">
                <div class="service-description">
                    <h3>Head Washing</h3>
                    <p>Experience a soothing head wash with premium hair care products and gentle scalp massage techniques. This service not only cleanses your scalp but also promotes blood circulation, leaving you feeling refreshed and invigorated. Perfect for a quick relaxation break that revitalizes both your hair and mind.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/hairdying.jpg" alt="Hair Dying">
                <div class="service-description">
                    <h3>Hair Dying</h3>
                    <p>Revamp your style with our professional hair dyeing service, using safe and high-quality products. We offer a variety of colors to choose from, whether you prefer subtle tones or bold, vibrant shades. Our stylists will work with you to select a color that enhances your features, making sure you walk out feeling confident and looking stunning.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/haircurling.jpg" alt="Hair Curling">
                <div class="service-description">
                    <h3>Hair Curling</h3>
                    <p>Create beautiful, voluminous curls with our expert hair curling service. Using modern curling technology, we ensure that your curls are long-lasting and look natural. Whether you want soft waves or defined curls, our team will bring out the best in your hair, adding an elegant touch to your overall look.</p>
                </div>
            </div>
        </div>



        <!-- Service Details -->
        <div id="haircut-info" class="service-info">

            <div class="box-wrapper">
                <h3>Staff for Hair Cut</h3>
                <div class="staff-box-container">
                    <div class="staff-box">
                        <img src="images/staff1.jpg" alt="Staff 1">
                        <div class="staff-name">Lưu Trường Văn</div>
                        <div class="staff-info">Age: 20</div>
                        <div class="staff-info">Experience: 8 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff2.jpg" alt="Staff 2">
                        <div class="staff-name">Trần Thành Đông</div>
                        <div class="staff-info">Age: 20</div>
                        <div class="staff-info">Experience: 6 years</div>
                        <div class="star-rating">★★★★★</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff3.jpg" alt="Staff 3">
                        <div class="staff-name">Đỗ Tấn Phát</div>
                        <div class="staff-info">Age: 20</div>
                        <div class="staff-info">Experience: 10 years</div>
                        <div class="star-rating">★★★☆☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff4.jpg" alt="Staff 4">
                        <div class="staff-name">Lê Nguyễn Minh Phúc</div>
                        <div class="staff-info">Age: 20</div>
                        <div class="staff-info">Experience: 9 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                </div>
            </div>

            <div class="box-wrapper">
                <h4>Hair Cut Service Price List</h4>
                <table class="price-table">
                    <tr>
                        <th>Service</th>
                        <th>Price (VND)</th>
                    </tr>
                    <tr>
                        <td>Men's Haircut</td>
                        <td>100,000</td>
                    </tr>
                    <tr>
                        <td>Women's Haircut</td>
                        <td>300,000</td>
                    </tr>
                    <tr>
                        <td>Children's Haircut</td>
                        <td>80,000</td>
                    </tr>
                </table>
            </div>

        </div>

        <!-- Makeup Section -->
        <div id="makeup-info" class="service-info">
            <div class="box-wrapper">
                <h3>Staff for Makeup</h3>
                <div class="staff-box-container">
                    <div class="staff-box">
                        <img src="images/staff5.jpg" alt="Staff 5">
                        <div class="staff-name">Sarah Lee</div>
                        <div class="staff-info">Age: 29</div>
                        <div class="staff-info">Experience: 7 years</div>
                        <div class="star-rating">★★★★★</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff6.jpg" alt="Staff 6">
                        <div class="staff-name">Chris Green</div>
                        <div class="staff-info">Age: 33</div>
                        <div class="staff-info">Experience: 9 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff7.jpg" alt="Staff 7">
                        <div class="staff-name">Anna Black</div>
                        <div class="staff-info">Age: 31</div>
                        <div class="staff-info">Experience: 8 years</div>
                        <div class="star-rating">★★★☆☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff8.jpg" alt="Staff 8">
                        <div class="staff-name">Tom Blue</div>
                        <div class="staff-info">Age: 34</div>
                        <div class="staff-info">Experience: 10 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                </div>
            </div>
            <div class="box-wrapper">
                <h4>Makeup Service Price List</h4>
                <table class="price-table">
                    <tr>
                        <th>Service</th>
                        <th>Price (VND)</th>
                    </tr>
                    <tr>
                        <td>Wedding Makeup</td>
                        <td>800,000</td>
                    </tr>
                    <tr>
                        <td>Party Makeup</td>
                        <td>600,000</td>
                    </tr>
                    <tr>
                        <td>Everyday Makeup</td>
                        <td>400,000</td>
                    </tr>
                </table>
            </div>

        </div>

        <!-- Massage Section -->
        <div id="massage-info" class="service-info">
            <div class="box-wrapper">
                <h3>Staff for Massage</h3>
                <div class="staff-box-container">
                    <div class="staff-box">
                        <img src="images/staff9.jpg" alt="Staff 9">
                        <div class="staff-name">Nguyễn Văn A</div>
                        <div class="staff-info">Age: 30</div>
                        <div class="staff-info">Experience: 5 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff10.jpg" alt="Staff 10">
                        <div class="staff-name">Trần Thị B</div>
                        <div class="staff-info">Age: 28</div>
                        <div class="staff-info">Experience: 6 years</div>
                        <div class="star-rating">★★★★★</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff11.jpg" alt="Staff 11">
                        <div class="staff-name">Lê Văn C</div>
                        <div class="staff-info">Age: 35</div>
                        <div class="staff-info">Experience: 7 years</div>
                        <div class="star-rating">★★★☆☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff12.jpg" alt="Staff 12">
                        <div class="staff-name">Phạm Thị D</div>
                        <div class="staff-info">Age: 32</div>
                        <div class="staff-info">Experience: 9 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                </div>
            </div>

            <div class="box-wrapper">
                <h4>Massage Service Price List</h4>
                <table class="price-table">
                    <tr>
                        <th>Service</th>
                        <th>Price (VND)</th>
                    </tr>
                    <tr>
                        <td>Relaxation Massage</td>
                        <td>300,000</td>
                    </tr>
                    <tr>
                        <td>Full Body Massage</td>
                        <td>600,000</td>
                    </tr>
                    <tr>
                        <td>Foot Massage</td>
                        <td>150,000</td>
                    </tr>
                </table>
            </div>

        </div>

        <!-- Head Washing Section -->
        <div id="headwashing-info" class="service-info">
            <div class="box-wrapper">
                <h3>Staff for Head Washing</h3>
                <div class="staff-box-container">
                    <div class="staff-box">
                        <img src="images/staff13.jpg" alt="Staff 13">
                        <div class="staff-name">Nguyễn Văn E</div>
                        <div class="staff-info">Age: 27</div>
                        <div class="staff-info">Experience: 4 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff14.jpg" alt="Staff 14">
                        <div class="staff-name">Lê Thị F</div>
                        <div class="staff-info">Age: 29</div>
                        <div class="staff-info">Experience: 5 years</div>
                        <div class="star-rating">★★★★★</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff15.jpg" alt="Staff 15">
                        <div class="staff-name">Trần Văn G</div>
                        <div class="staff-info">Age: 31</div>
                        <div class="staff-info">Experience: 6 years</div>
                        <div class="star-rating">★★★☆☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff16.jpg" alt="Staff 16">
                        <div class="staff-name">Phạm Thị H</div>
                        <div class="staff-info">Age: 26</div>
                        <div class="staff-info">Experience: 3 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                </div>
            </div>

            <div class="box-wrapper">
                <h4>Head Washing Service Price List</h4>
                <table class="price-table">
                    <tr>
                        <th>Service</th>
                        <th>Price (VND)</th>
                    </tr>
                    <tr>
                        <td>Relaxing Head Wash</td>
                        <td>100,000</td>
                    </tr>
                    <tr>
                        <td>Head Wash & Scalp Massage</td>
                        <td>200,000</td>
                    </tr>
                </table>
            </div>

        </div>

        <!-- Hair Dying Section -->
        <div id="hairdying-info" class="service-info">
            <div class="box-wrapper">
                <h3>Staff for Hair Dying</h3>
                <div class="staff-box-container">
                    <div class="staff-box">
                        <img src="images/staff17.jpg" alt="Staff 17">
                        <div class="staff-name">Nguyễn Văn I</div>
                        <div class="staff-info">Age: 28</div>
                        <div class="staff-info">Experience: 5 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff18.jpg" alt="Staff 18">
                        <div class="staff-name">Lê Thị J</div>
                        <div class="staff-info">Age: 34</div>
                        <div class="staff-info">Experience: 8 years</div>
                        <div class="star-rating">★★★★★</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff19.jpg" alt="Staff 19">
                        <div class="staff-name">Trần Văn K</div>
                        <div class="staff-info">Age: 30</div>
                        <div class="staff-info">Experience: 6 years</div>
                        <div class="star-rating">★★★☆☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff20.jpg" alt="Staff 20">
                        <div class="staff-name">Phạm Thị L</div>
                        <div class="staff-info">Age: 25</div>
                        <div class="staff-info">Experience: 4 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                </div>
            </div>

            <div class="box-wrapper">
                <h4>Hair Dyeing Service Price List</h4>
                <table class="price-table">
                    <tr>
                        <th>Service</th>
                        <th>Price (VND)</th>
                    </tr>
                    <tr>
                        <td>Standard Hair Dye</td>
                        <td>600,000</td>
                    </tr>
                    <tr>
                        <td>Ombre Dye</td>
                        <td>1,200,000</td>
                    </tr>
                    <tr>
                        <td>Balayage Dye</td>
                        <td>1,500,000</td>
                    </tr>
                </table>
            </div>

        </div>

        <!-- Hair Curling Section -->
        <div id="haircurling-info" class="service-info">
            <div class="box-wrapper">
                <h3>Staff for Hair Curling</h3>
                <div class="staff-box-container">
                    <div class="staff-box">
                        <img src="images/staff21.jpg" alt="Staff 21">
                        <div class="staff-name">Nguyễn Văn M</div>
                        <div class="staff-info">Age: 26</div>
                        <div class="staff-info">Experience: 4 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff22.jpg" alt="Staff 22">
                        <div class="staff-name">Lê Thị N</div>
                        <div class="staff-info">Age: 29</div>
                        <div class="staff-info">Experience: 5 years</div>
                        <div class="star-rating">★★★★★</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff23.jpg" alt="Staff 23">
                        <div class="staff-name">Trần Văn O</div>
                        <div class="staff-info">Age: 32</div>
                        <div class="staff-info">Experience: 6 years</div>
                        <div class="star-rating">★★★☆☆</div>
                        <button>Select</button>
                    </div>
                    <div class="staff-box">
                        <img src="images/staff24.jpg" alt="Staff 24">
                        <div class="staff-name">Phạm Thị P</div>
                        <div class="staff-info">Age: 28</div>
                        <div class="staff-info">Experience: 7 years</div>
                        <div class="star-rating">★★★★☆</div>
                        <button>Select</button>
                    </div>
                </div>
            </div>

            <div class="box-wrapper">
                <h4>Hair Curling Service Price List</h4>
                <table class="price-table">
                    <tr>
                        <th>Service</th>
                        <th>Price (VND)</th>
                    </tr>
                    <tr>
                        <td>Voluminous Curl</td>
                        <td>800,000</td>
                    </tr>
                    <tr>
                        <td>End Curl</td>
                        <td>600,000</td>
                    </tr>
                    <tr>
                        <td>Water Wave Curl</td>
                        <td>1,000,000</td>
                    </tr>
                </table>
            </div>

        </div>
    </div>
</div>


</body>
</html>

<script src="view/js/show-content-service.js"></script>
