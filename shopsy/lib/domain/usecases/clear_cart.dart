import '../repositories/cart_repository.dart';

class ClearCartUseCase {
  final CartRepository repository;
  ClearCartUseCase(this.repository);

  void call() => repository.clear();
}