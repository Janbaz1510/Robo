import '../repositories/cart_repository.dart';

class RemoveFromCartUseCase {
  final CartRepository repository;
  RemoveFromCartUseCase(this.repository);

  void call(String productId) => repository.removeOne(productId);
}