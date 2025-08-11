import '../entities/product.dart';
import '../repositories/cart_repository.dart';

class AddToCartUseCase {
  final CartRepository repository;
  AddToCartUseCase(this.repository);

  void call(Product product) => repository.add(product);
}