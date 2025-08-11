import '../repositories/cart_repository.dart';

class GetCartTotalUseCase {
  final CartRepository repository;
  GetCartTotalUseCase(this.repository);

  double call() => repository.totalPrice;
}