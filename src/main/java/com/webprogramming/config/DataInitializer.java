package com.webprogramming.config;

import com.webprogramming.entity.Category;
import com.webprogramming.entity.Product;
import com.webprogramming.entity.Role;
import com.webprogramming.entity.User;
import com.webprogramming.repository.CategoryRepository;
import com.webprogramming.repository.ProductRepository;
import com.webprogramming.repository.RoleRepository;
import com.webprogramming.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

	@Bean
	public CommandLineRunner init(PasswordEncoder passwordEncoder,
			@Value("${DEMO_USERNAME:user01}") String demoUsername,
			@Value("${DEMO_EMAIL:user01@gmail.com}") String demoEmail,
			@Value("${DEMO_PASSWORD:123456}") String demoPassword,
			@Value("${DEMO_FULL_NAME:Trần Thị Phương Trang}") String demoFullName,
			@Value("${DEMO_IMAGES:/images/avatar-default.svg}") String demoImages) {
		return args -> {
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_USER").build()));

			roleRepository.findByName("ROLE_ADMIN")
					.orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_ADMIN").build()));

			if (userRepository.findByUsername(demoUsername).isEmpty()
					&& userRepository.findByEmail(demoEmail).isEmpty()) {

				User user = User.builder().username(demoUsername).email(demoEmail)
						.password(passwordEncoder.encode(demoPassword)).fullName(demoFullName).images(demoImages)
						.role(userRole).enabled(true).build();

				userRepository.save(user);
			}

			Category men = getOrCreateCategory("Quần áo nam", "categories/male-icon.png");

			Category women = getOrCreateCategory("Quần áo nữ", "categories/female-icon.png");

			Category accessories = getOrCreateCategory("Phụ kiện", "categories/accessories-icon.png");

			createProductIfAbsent("P001", "Quần Jeans", 399000, "Quần jeans phong cách casual.",
					"products/1788243139039_jeans.png", 50, men);

			createProductIfAbsent("P002", "Dép Slipper", 199000, "Dép slipper đơn giản, dễ phối đồ.",
					"products/1788244396888_slipper.png", 40, accessories);

			createProductIfAbsent("P003", "Áo thun xanh", 229000, "Áo thun màu xanh phong cách basic.",
					"products/1788243790881_blue_tee.png", 60, men);

			createProductIfAbsent("P004", "Khăn Bandana", 99000, "Khăn bandana phụ kiện thời trang.",
					"products/1788949164620_bandana.png", 35, accessories);

			createProductIfAbsent("P005", "Túi Tote", 249000, "Túi tote tiện dụng hằng ngày.",
					"products/1788243055298_totebag.png", 45, accessories);

			createProductIfAbsent("P006", "Mũ lưỡi trai", 179000, "Mũ lưỡi trai phong cách năng động.",
					"products/1788244314014_cap.png", 30, accessories);

			createProductIfAbsent("P007", "Áo Waffle dài tay", 329000, "Áo waffle dài tay chất liệu nhẹ.",
					"products/1788244199840_waffle_longsleeve.png", 30, men);

			createProductIfAbsent("P008", "Ví da", 289000, "Ví da thiết kế nhỏ gọn.",
					"products/1788242993835_leather_wallet.png", 25, accessories);

			createProductIfAbsent("P009", "Quần Short", 269000, "Quần short thoải mái cho ngày thường.",
					"products/1788244084789_short.png", 40, men);

			createProductIfAbsent("P010", "Quần Flare", 349000, "Quần flare phong cách hiện đại.",
					"products/1788243696588_flare.png", 35, women);

			createProductIfAbsent("P011", "Áo Hoodie Zip", 429000, "Áo hoodie zip phù hợp thời tiết se lạnh.",
					"products/1788243287656_hoodie_zip.png", 35, women);

			createProductIfAbsent("P012", "Áo thun sọc hồng", 239000, "Áo thun họa tiết sọc.",
					"products/1788242802583_pink_triple_striped_tee.png", 40, women);

			createProductIfAbsent("P013", "Áo thun sọc ba màu", 239000, "Áo thun sọc ba màu phong cách trẻ trung.",
					"products/1788242562163_triple_striped_tee.png", 40, women);

			createProductIfAbsent("P014", "Áo Raglan cam", 259000, "Áo raglan tay phối màu.",
					"products/1788243415932_orange_raglan.png", 35, women);

			createProductIfAbsent("P015", "Áo Regular Fit", 229000, "Áo thun regular fit cơ bản.",
					"products/1788242888567_regularfit01.png", 50, men);

			createProductIfAbsent("P016", "Áo khoác Nylon", 499000, "Áo khoác nylon nhẹ, tiện dụng.",
					"products/1788243546966_nylon_jacket.png", 25, men);

			createProductIfAbsent("P017", "Áo Grey Fit", 319000, "Thiết kế grey fit phong cách casual.",
					"products/1788243995893_grey_fit.png", 30, women);

			createProductIfAbsent("P018", "Áo Regular Fit Basic", 229000, "Áo regular fit basic.",
					"products/1788171763309_regularfit01.png", 45, men);
		};
	}

	private Category getOrCreateCategory(String name, String image) {
		return categoryRepository.findByCategoryName(name)
				.orElseGet(() -> categoryRepository.save(new Category(0, name, image, 1, null)));
	}

	private void createProductIfAbsent(String productId, String productName, double price, String description,
			String image, int quantity, Category category) {

		if (productRepository.existsById(productId)) {
			return;
		}

		Product product = new Product();
		product.setProductId(productId);
		product.setProductName(productName);
		product.setPrice(price);
		product.setDescription(description);
		product.setImages(image);
		product.setQuantity(quantity);
		product.setSold(0);
		product.setCreatedAt(LocalDateTime.now());
		product.setCategory(category);

		productRepository.save(product);
	}
}
