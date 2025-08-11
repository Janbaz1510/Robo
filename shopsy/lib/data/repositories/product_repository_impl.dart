import '../../domain/entities/product.dart';
import '../../domain/repositories/product_repository.dart';
import '../datasources/local_product_data_source.dart';
import '../mappers/product_mapper.dart';

class ProductRepositoryImpl implements ProductRepository {
  final LocalProductDataSource dataSource;
  List<Product>? _cache;

  ProductRepositoryImpl(this.dataSource);

  @override
  Future<List<Product>> getProducts() async {
    if (_cache != null) return _cache!;
    final raw = await dataSource.loadProductsRaw();
    _cache = raw.map(ProductMapper.fromJson).toList(growable: false);
    return _cache!;
  }

  @override
  Future<Product?> getProductById(String id) async {
    final products = await getProducts();
    try {
      return products.firstWhere((p) => p.id == id);
    } catch (_) {
      return null;
    }
  }
}