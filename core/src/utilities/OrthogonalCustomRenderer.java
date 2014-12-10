package utilities;

import static com.badlogic.gdx.graphics.g2d.Batch.C1;
import static com.badlogic.gdx.graphics.g2d.Batch.C2;
import static com.badlogic.gdx.graphics.g2d.Batch.C3;
import static com.badlogic.gdx.graphics.g2d.Batch.C4;
import static com.badlogic.gdx.graphics.g2d.Batch.U1;
import static com.badlogic.gdx.graphics.g2d.Batch.U2;
import static com.badlogic.gdx.graphics.g2d.Batch.U3;
import static com.badlogic.gdx.graphics.g2d.Batch.U4;
import static com.badlogic.gdx.graphics.g2d.Batch.V1;
import static com.badlogic.gdx.graphics.g2d.Batch.V2;
import static com.badlogic.gdx.graphics.g2d.Batch.V3;
import static com.badlogic.gdx.graphics.g2d.Batch.V4;
import static com.badlogic.gdx.graphics.g2d.Batch.X1;
import static com.badlogic.gdx.graphics.g2d.Batch.X2;
import static com.badlogic.gdx.graphics.g2d.Batch.X3;
import static com.badlogic.gdx.graphics.g2d.Batch.X4;
import static com.badlogic.gdx.graphics.g2d.Batch.Y1;
import static com.badlogic.gdx.graphics.g2d.Batch.Y2;
import static com.badlogic.gdx.graphics.g2d.Batch.Y3;
import static com.badlogic.gdx.graphics.g2d.Batch.Y4;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.orangeegames.suikorm.SuikodenRM;

import entities.DrawableBox2D;
import entities.Player;

public class OrthogonalCustomRenderer extends BatchTiledMapRenderer {

	private float[] vertices = new float[20];

	public OrthogonalCustomRenderer(TiledMap map) {
		super(map);
	}

	public OrthogonalCustomRenderer(TiledMap map, SpriteBatch spriteBatch) {
		super(map, spriteBatch);
	}

	public OrthogonalCustomRenderer(TiledMap map, float unitScale) {
		super(map, unitScale);
	}

	public OrthogonalCustomRenderer(TiledMap map, float unitScale,
			SpriteBatch spriteBatch) {
		super(map, unitScale, spriteBatch);
	}

	@Override
	public void renderObject(MapObject object) {

	}

	@Override
	public void renderTileLayer(TiledMapTileLayer layer) {
		final Color batchColor = batch.getColor();
		final float color = Color.toFloatBits(batchColor.r, batchColor.g,
				batchColor.b, batchColor.a * layer.getOpacity());

		final int layerWidth = layer.getWidth();
		final int layerHeight = layer.getHeight();

		final float layerTileWidth = layer.getTileWidth() * unitScale;
		final float layerTileHeight = layer.getTileHeight() * unitScale;

		final int col1 = Math.max(0, (int) (viewBounds.x / layerTileWidth));
		final int col2 = Math.min(layerWidth, (int) ((viewBounds.x
				+ viewBounds.width + layerTileWidth) / layerTileWidth));

		final int row1 = Math.max(0, (int) (viewBounds.y / layerTileHeight));
		final int row2 = Math.min(layerHeight, (int) ((viewBounds.y
				+ viewBounds.height + layerTileHeight) / layerTileHeight)); 

		float y = row1 * layerTileHeight;
		float xStart = col1 * layerTileWidth;
		final float[] vertices = this.vertices;

		for (int row = row1; row < row2; row++) {
			float x = xStart;
			for (int col = col1; col < col2; col++) {
				final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
				if (cell == null) {
					x += layerTileWidth;
					continue;
				}
				final TiledMapTile tile = cell.getTile();
				if (tile != null) {
					final boolean flipX = cell.getFlipHorizontally();
					final boolean flipY = cell.getFlipVertically();
					final int rotations = cell.getRotation();

					TextureRegion region = tile.getTextureRegion();

					float x1 = x;
					float y1 = y;
					float x2 = x1 + region.getRegionWidth() * unitScale;
					float y2 = y1 + region.getRegionHeight() * unitScale;

					float u1 = region.getU();
					float v1 = region.getV2();
					float u2 = region.getU2();
					float v2 = region.getV();

					vertices[X1] = x1;
					vertices[Y1] = y1;
					vertices[C1] = color;
					vertices[U1] = u1;
					vertices[V1] = v1;

					vertices[X2] = x1;
					vertices[Y2] = y2;
					vertices[C2] = color;
					vertices[U2] = u1;
					vertices[V2] = v2;

					vertices[X3] = x2;
					vertices[Y3] = y2;
					vertices[C3] = color;
					vertices[U3] = u2;
					vertices[V3] = v2;

					vertices[X4] = x2;
					vertices[Y4] = y1;
					vertices[C4] = color;
					vertices[U4] = u2;
					vertices[V4] = v1;

					if (flipX) {
						float temp = vertices[U1];
						vertices[U1] = vertices[U3];
						vertices[U3] = temp;
						temp = vertices[U2];
						vertices[U2] = vertices[U4];
						vertices[U4] = temp;
					}
					if (flipY) {
						float temp = vertices[V1];
						vertices[V1] = vertices[V3];
						vertices[V3] = temp;
						temp = vertices[V2];
						vertices[V2] = vertices[V4];
						vertices[V4] = temp;
					}
					if (rotations != 0) {
						switch (rotations) {
						case Cell.ROTATE_90: {
							float tempV = vertices[V1];
							vertices[V1] = vertices[V2];
							vertices[V2] = vertices[V3];
							vertices[V3] = vertices[V4];
							vertices[V4] = tempV;

							float tempU = vertices[U1];
							vertices[U1] = vertices[U2];
							vertices[U2] = vertices[U3];
							vertices[U3] = vertices[U4];
							vertices[U4] = tempU;
							break;
						}
						case Cell.ROTATE_180: {
							float tempU = vertices[U1];
							vertices[U1] = vertices[U3];
							vertices[U3] = tempU;
							tempU = vertices[U2];
							vertices[U2] = vertices[U4];
							vertices[U4] = tempU;
							float tempV = vertices[V1];
							vertices[V1] = vertices[V3];
							vertices[V3] = tempV;
							tempV = vertices[V2];
							vertices[V2] = vertices[V4];
							vertices[V4] = tempV;
							break;
						}
						case Cell.ROTATE_270: {
							float tempV = vertices[V1];
							vertices[V1] = vertices[V4];
							vertices[V4] = vertices[V3];
							vertices[V3] = vertices[V2];
							vertices[V2] = tempV;

							float tempU = vertices[U1];
							vertices[U1] = vertices[U4];
							vertices[U4] = vertices[U3];
							vertices[U3] = vertices[U2];
							vertices[U2] = tempU;
							break;
						}
						}
					}
					batch.draw(region.getTexture(), vertices, 0, 20);
					x += layerTileWidth;
				}
			}
			y += layerTileHeight;
		}
	}
	
	public void renderTopTileLayer(TiledMapTileLayer layer, Body body, Player player) {
		
	}
	
	public void renderBottomTileLayer(TiledMapTileLayer layer, Body body, Player player) {
		
	}
	
public void renderTileLayer(ArrayList<TiledMapTileLayer> layers, Player player) {
		
		// From here -->
		for(TiledMapTileLayer layer : layers) {
			final Color batchColor = batch.getColor();
			final float color = Color.toFloatBits(batchColor.r, batchColor.g,
					batchColor.b, batchColor.a * layer.getOpacity());
	
			final int layerWidth = layer.getWidth();
			final int layerHeight = layer.getHeight();
	
			final float layerTileWidth = layer.getTileWidth() * unitScale;
			final float layerTileHeight = layer.getTileHeight() * unitScale;
	
			final int col1 = (int) Math.max(0, (int) (viewBounds.x / layerTileWidth) - layerTileWidth);
			final int col2 = Math.min(layerWidth, (int) ((viewBounds.x
					+ viewBounds.width + layerTileWidth) / layerTileWidth));
		
			// <-- To here, nothing has changed from the original OrthogonalTiledMapRenderer
			
			//final int row1 = Math.max(0, (int) (viewBounds.y / layerTileHeight)-(int) layerTileHeight);
			final int row2 = Math.min(layerHeight, (int) ((viewBounds.y
					+ viewBounds.height) / layerTileHeight)); 
			
			// Instead of looking into the first row first, we look into the last row first.
			// Didn't find a good way to work around this.
			float y = row2* layerTileHeight;
			float xStart = col1 * layerTileWidth;
			final float[] vertices = this.vertices;
	
			// Our first not final int.
			int playersRow = (int) ((player.getY())/layerTileHeight);
			
			// Once again, instead of going from the beginning to the end, we go
			// from the end to the beginning
			for (int row = row2; row > playersRow; row--) {
				float x = xStart;
				for (int col = col1; col < col2; col++) {
					final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
					if (cell == null) {
						x += layerTileWidth;
						continue;
					}
					final TiledMapTile tile = cell.getTile();
					if (tile != null) {
						final boolean flipX = cell.getFlipHorizontally();
						final boolean flipY = cell.getFlipVertically();
						final int rotations = cell.getRotation();
	
						TextureRegion region = tile.getTextureRegion();
	
						float x1 = x;
						float y1 = y;
						float x2 = x1 + region.getRegionWidth() * unitScale;
						float y2 = y1 + region.getRegionHeight() * unitScale;
	
						float u1 = region.getU();
						float v1 = region.getV2();
						float u2 = region.getU2();
						float v2 = region.getV();
	
						vertices[X1] = x1;
						vertices[Y1] = y1;
						vertices[C1] = color;
						vertices[U1] = u1;
						vertices[V1] = v1;
	
						vertices[X2] = x1;
						vertices[Y2] = y2;
						vertices[C2] = color;
						vertices[U2] = u1;
						vertices[V2] = v2;
	
						vertices[X3] = x2;
						vertices[Y3] = y2;
						vertices[C3] = color;
						vertices[U3] = u2;
						vertices[V3] = v2;
	
						vertices[X4] = x2;
						vertices[Y4] = y1;
						vertices[C4] = color;
						vertices[U4] = u2;
						vertices[V4] = v1;
	
						if (flipX) {
							float temp = vertices[U1];
							vertices[U1] = vertices[U3];
							vertices[U3] = temp;
							temp = vertices[U2];
							vertices[U2] = vertices[U4];
							vertices[U4] = temp;
						}
						if (flipY) {
							float temp = vertices[V1];
							vertices[V1] = vertices[V3];
							vertices[V3] = temp;
							temp = vertices[V2];
							vertices[V2] = vertices[V4];
							vertices[V4] = temp;
						}
						if (rotations != 0) {
							switch (rotations) {
							case Cell.ROTATE_90: {
								float tempV = vertices[V1];
								vertices[V1] = vertices[V2];
								vertices[V2] = vertices[V3];
								vertices[V3] = vertices[V4];
								vertices[V4] = tempV;
	
								float tempU = vertices[U1];
								vertices[U1] = vertices[U2];
								vertices[U2] = vertices[U3];
								vertices[U3] = vertices[U4];
								vertices[U4] = tempU;
								break;
							}
							case Cell.ROTATE_180: {
								float tempU = vertices[U1];
								vertices[U1] = vertices[U3];
								vertices[U3] = tempU;
								tempU = vertices[U2];
								vertices[U2] = vertices[U4];
								vertices[U4] = tempU;
								float tempV = vertices[V1];
								vertices[V1] = vertices[V3];
								vertices[V3] = tempV;
								tempV = vertices[V2];
								vertices[V2] = vertices[V4];
								vertices[V4] = tempV;
								break;
							}
							case Cell.ROTATE_270: {
								float tempV = vertices[V1];
								vertices[V1] = vertices[V4];
								vertices[V4] = vertices[V3];
								vertices[V3] = vertices[V2];
								vertices[V2] = tempV;
	
								float tempU = vertices[U1];
								vertices[U1] = vertices[U4];
								vertices[U4] = vertices[U3];
								vertices[U3] = vertices[U2];
								vertices[U2] = tempU;
								break;
							}
							}
						}
						batch.draw(region.getTexture(), vertices, 0, 20);
						x += layerTileWidth;
					}
				}
				// Instead of adding we subtract, because as I've stated, we're going from
				// the end.
				y -= layerTileHeight;
			}
		}
		/*
		 * OBS! OBS!
		 * This is the part you have to do manually! I use player.draw to draw my player, you
		 * might use the batch to draw your player. Either way, you have to edit this
		 * single line for your 2.5D game to work!
		 */
		player.draw(this.batch);
		/*
		 * OBS! OBS!
		 */
		
		
		
		// We're going from the row that the player is standing on, all the way to the beginning
		for(TiledMapTileLayer layer : layers) {
			final Color batchColor = batch.getColor();
			final float color = Color.toFloatBits(batchColor.r, batchColor.g,
					batchColor.b, batchColor.a * layer.getOpacity());
	
			final int layerWidth = layer.getWidth();
			final int layerHeight = layer.getHeight();
	
			final float layerTileWidth = layer.getTileWidth() * unitScale;
			final float layerTileHeight = layer.getTileHeight() * unitScale;
	
			final int col1 = (int) Math.max(0, (int) (viewBounds.x / layerTileWidth) - layerTileWidth);
			final int col2 = Math.min(layerWidth, (int) ((viewBounds.x
					+ viewBounds.width + layerTileWidth) / layerTileWidth));
		
			// <-- To here, nothing has changed from the original OrthogonalTiledMapRenderer
			
			final int row1 = Math.max(0, (int) (viewBounds.y / layerTileHeight)-(int) layerTileHeight);
			final int row2 = Math.min(layerHeight, (int) ((viewBounds.y
					+ viewBounds.height) / layerTileHeight)); 
			
			// Instead of looking into the first row first, we look into the last row first.
			// Didn't find a good way to work around this.
			float y = row2* layerTileHeight;
			float xStart = col1 * layerTileWidth;
			final float[] vertices = this.vertices;
	
			// Our first not final int.
			int playersRow = (int) (player.getY()/layerTileHeight);
			for (int row = row2; row > playersRow; row--) {
				y -= layerTileHeight;
			}
			
			// Once again, instead of going from the beginning to the end, we go
			// from the end to the beginning
			for (int row = playersRow; row > row1; row--) {
				float x = xStart;
				for (int col = col1; col < col2; col++) {
					final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
					if (cell == null) {
						x += layerTileWidth;
						continue;
					}
					final TiledMapTile tile = cell.getTile();
					if (tile != null) {
						final boolean flipX = cell.getFlipHorizontally();
						final boolean flipY = cell.getFlipVertically();
						final int rotations = cell.getRotation();

						TextureRegion region = tile.getTextureRegion();

						float x1 = x;
						float y1 = y;
						float x2 = x1 + region.getRegionWidth() * unitScale;
						float y2 = y1 + region.getRegionHeight() * unitScale;

						float u1 = region.getU();
						float v1 = region.getV2();
						float u2 = region.getU2();
						float v2 = region.getV();

						vertices[X1] = x1;
						vertices[Y1] = y1;
						vertices[C1] = color;
						vertices[U1] = u1;
						vertices[V1] = v1;

						vertices[X2] = x1;
						vertices[Y2] = y2;
						vertices[C2] = color;
						vertices[U2] = u1;
						vertices[V2] = v2;

						vertices[X3] = x2;
						vertices[Y3] = y2;
						vertices[C3] = color;
						vertices[U3] = u2;
						vertices[V3] = v2;

						vertices[X4] = x2;
						vertices[Y4] = y1;
						vertices[C4] = color;
						vertices[U4] = u2;
						vertices[V4] = v1;

						if (flipX) {
							float temp = vertices[U1];
							vertices[U1] = vertices[U3];
							vertices[U3] = temp;
							temp = vertices[U2];
							vertices[U2] = vertices[U4];
							vertices[U4] = temp;
						}
						if (flipY) {
							float temp = vertices[V1];
							vertices[V1] = vertices[V3];
							vertices[V3] = temp;
							temp = vertices[V2];
							vertices[V2] = vertices[V4];
							vertices[V4] = temp;
						}
						if (rotations != 0) {
							switch (rotations) {
							case Cell.ROTATE_90: {
								float tempV = vertices[V1];
								vertices[V1] = vertices[V2];
								vertices[V2] = vertices[V3];
								vertices[V3] = vertices[V4];
								vertices[V4] = tempV;

								float tempU = vertices[U1];
								vertices[U1] = vertices[U2];
								vertices[U2] = vertices[U3];
								vertices[U3] = vertices[U4];
								vertices[U4] = tempU;
								break;
							}
							case Cell.ROTATE_180: {
								float tempU = vertices[U1];
								vertices[U1] = vertices[U3];
								vertices[U3] = tempU;
								tempU = vertices[U2];
								vertices[U2] = vertices[U4];
								vertices[U4] = tempU;
								float tempV = vertices[V1];
								vertices[V1] = vertices[V3];
								vertices[V3] = tempV;
								tempV = vertices[V2];
								vertices[V2] = vertices[V4];
								vertices[V4] = tempV;
								break;
							}
							case Cell.ROTATE_270: {
								float tempV = vertices[V1];
								vertices[V1] = vertices[V4];
								vertices[V4] = vertices[V3];
								vertices[V3] = vertices[V2];
								vertices[V2] = tempV;

								float tempU = vertices[U1];
								vertices[U1] = vertices[U4];
								vertices[U4] = vertices[U3];
								vertices[U3] = vertices[U2];
								vertices[U2] = tempU;
								break;
							}
							}
						}
						batch.draw(region.getTexture(), vertices, 0, 20);
						x += layerTileWidth;
					}
				}
				// And we're still subtracting.
				y -= layerTileHeight;
			}
		}
		
	}


	public void renderTileLayer(ArrayList<TiledMapTileLayer> layers, ArrayList<DrawableBox2D> drawableBoxes) {
		
		Collections.sort(drawableBoxes);
		unitScale = SuikodenRM.scale;
		for(int i = 0; i < drawableBoxes.size(); i++) {
			DrawableBox2D db2d = drawableBoxes.get(i);
			
			for(TiledMapTileLayer layer : layers) {
				final Color batchColor = batch.getColor();
				final float color = Color.toFloatBits(batchColor.r, batchColor.g,
						batchColor.b, batchColor.a * layer.getOpacity());
		
				final int layerWidth = layer.getWidth();
				final int layerHeight = layer.getHeight();
		
				final float layerTileWidth = layer.getTileWidth() * unitScale;
				final float layerTileHeight = layer.getTileHeight() * unitScale;
		
				final int col1 = (int) Math.max(0, (int) (viewBounds.x / layerTileWidth) - layerTileWidth);
				final int col2 = Math.min(layerWidth, (int) ((viewBounds.x
						+ viewBounds.width + layerTileWidth) / layerTileWidth));
			
				// <-- To here, nothing has changed from the original OrthogonalTiledMapRenderer
				
				//final int row1 = Math.max(0, (int) (viewBounds.y / layerTileHeight)-(int) layerTileHeight);
				final int row2 = Math.min(layerHeight, (int) ((viewBounds.y
						+ viewBounds.height) / layerTileHeight)); 
				
				// Instead of looking into the first row first, we look into the last row first.
				// Didn't find a good way to work around this.
				float y = row2 * layerTileHeight;
				float xStart = col1 * layerTileWidth;
				final float[] vertices = this.vertices;
		
				// Our first not final int.
				int playersRow = (int) ((db2d.getBody().getPosition().y)/layerTileHeight);
				int previousRow;
				if(i == 0) {
					previousRow = row2;
					//previousRow = (int) (viewBounds.height / layerTileHeight);
				} else {
					previousRow = (int) ((drawableBoxes.get(i-1).getBody().getPosition().y)/layerTileHeight);
				}
				
				for (int row = row2; row > previousRow; row--) {
					y -= layerTileHeight;
				}
				
				// Once again, instead of going from the beginning to the end, we go
				// from the end to the beginning
				for (int row = previousRow; row > playersRow; row--) {
					float x = xStart;
					for (int col = col1; col < col2; col++) {
						final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
						if (cell == null) {
							x += layerTileWidth;
							continue;
						}
						final TiledMapTile tile = cell.getTile();
						if (tile != null) {
							final boolean flipX = cell.getFlipHorizontally();
							final boolean flipY = cell.getFlipVertically();
							final int rotations = cell.getRotation();
		
							TextureRegion region = tile.getTextureRegion();
		
							float x1 = x;
							float y1 = y;
							float x2 = x1 + region.getRegionWidth() * unitScale;
							float y2 = y1 + region.getRegionHeight() * unitScale;
		
							float u1 = region.getU();
							float v1 = region.getV2();
							float u2 = region.getU2();
							float v2 = region.getV();
		
							vertices[X1] = x1;
							vertices[Y1] = y1;
							vertices[C1] = color;
							vertices[U1] = u1;
							vertices[V1] = v1;
		
							vertices[X2] = x1;
							vertices[Y2] = y2;
							vertices[C2] = color;
							vertices[U2] = u1;
							vertices[V2] = v2;
		
							vertices[X3] = x2;
							vertices[Y3] = y2;
							vertices[C3] = color;
							vertices[U3] = u2;
							vertices[V3] = v2;
		
							vertices[X4] = x2;
							vertices[Y4] = y1;
							vertices[C4] = color;
							vertices[U4] = u2;
							vertices[V4] = v1;
		
							if (flipX) {
								float temp = vertices[U1];
								vertices[U1] = vertices[U3];
								vertices[U3] = temp;
								temp = vertices[U2];
								vertices[U2] = vertices[U4];
								vertices[U4] = temp;
							}
							if (flipY) {
								float temp = vertices[V1];
								vertices[V1] = vertices[V3];
								vertices[V3] = temp;
								temp = vertices[V2];
								vertices[V2] = vertices[V4];
								vertices[V4] = temp;
							}
							if (rotations != 0) {
								switch (rotations) {
								case Cell.ROTATE_90: {
									float tempV = vertices[V1];
									vertices[V1] = vertices[V2];
									vertices[V2] = vertices[V3];
									vertices[V3] = vertices[V4];
									vertices[V4] = tempV;
		
									float tempU = vertices[U1];
									vertices[U1] = vertices[U2];
									vertices[U2] = vertices[U3];
									vertices[U3] = vertices[U4];
									vertices[U4] = tempU;
									break;
								}
								case Cell.ROTATE_180: {
									float tempU = vertices[U1];
									vertices[U1] = vertices[U3];
									vertices[U3] = tempU;
									tempU = vertices[U2];
									vertices[U2] = vertices[U4];
									vertices[U4] = tempU;
									float tempV = vertices[V1];
									vertices[V1] = vertices[V3];
									vertices[V3] = tempV;
									tempV = vertices[V2];
									vertices[V2] = vertices[V4];
									vertices[V4] = tempV;
									break;
								}
								case Cell.ROTATE_270: {
									float tempV = vertices[V1];
									vertices[V1] = vertices[V4];
									vertices[V4] = vertices[V3];
									vertices[V3] = vertices[V2];
									vertices[V2] = tempV;
		
									float tempU = vertices[U1];
									vertices[U1] = vertices[U4];
									vertices[U4] = vertices[U3];
									vertices[U3] = vertices[U2];
									vertices[U2] = tempU;
									break;
								}
								}
							}
							batch.draw(region.getTexture(), vertices, 0, 20);
							x += layerTileWidth;
						}
					}
					// Instead of adding we subtract, because as I've stated, we're going from
					// the end.
					y -= layerTileHeight;
				}
				
				db2d.draw(batch);
			}
			
		}
		
		for(TiledMapTileLayer layer : layers) {
			final Color batchColor = batch.getColor();
			final float color = Color.toFloatBits(batchColor.r, batchColor.g,
					batchColor.b, batchColor.a * layer.getOpacity());
	
			final int layerWidth = layer.getWidth();
			final int layerHeight = layer.getHeight();
	
			final float layerTileWidth = layer.getTileWidth() * unitScale;
			final float layerTileHeight = layer.getTileHeight() * unitScale;
	
			final int col1 = (int) Math.max(0, (int) (viewBounds.x / layerTileWidth) - layerTileWidth);
			final int col2 = Math.min(layerWidth, (int) ((viewBounds.x
					+ viewBounds.width + layerTileWidth) / layerTileWidth));
		
			// <-- To here, nothing has changed from the original OrthogonalTiledMapRenderer
			
			final int row1 = Math.max(0, (int) (viewBounds.y / layerTileHeight)-(int) layerTileHeight);
			final int row2 = Math.min(layerHeight, (int) ((viewBounds.y
					+ viewBounds.height) / layerTileHeight)); 
			
			// Instead of looking into the first row first, we look into the last row first.
			// Didn't find a good way to work around this.
			float y = row2* layerTileHeight;
			float xStart = col1 * layerTileWidth;
			final float[] vertices = this.vertices;
	
			// Our first not final int.
			int lastRows = (int) (drawableBoxes.get(drawableBoxes.size() - 1).getBody().getPosition().y/layerTileHeight);
			for (int row = row2; row > lastRows; row--) {
				y -= layerTileHeight;
			}
			
			// Once again, instead of going from the beginning to the end, we go
			// from the end to the beginning
			for (int row = lastRows; row > row1; row--) {
				float x = xStart;
				for (int col = col1; col < col2; col++) {
					final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
					if (cell == null) {
						x += layerTileWidth;
						continue;
					}
					final TiledMapTile tile = cell.getTile();
					if (tile != null) {
						final boolean flipX = cell.getFlipHorizontally();
						final boolean flipY = cell.getFlipVertically();
						final int rotations = cell.getRotation();

						TextureRegion region = tile.getTextureRegion();

						float x1 = x;
						float y1 = y;
						float x2 = x1 + region.getRegionWidth() * unitScale;
						float y2 = y1 + region.getRegionHeight() * unitScale;

						float u1 = region.getU();
						float v1 = region.getV2();
						float u2 = region.getU2();
						float v2 = region.getV();

						vertices[X1] = x1;
						vertices[Y1] = y1;
						vertices[C1] = color;
						vertices[U1] = u1;
						vertices[V1] = v1;

						vertices[X2] = x1;
						vertices[Y2] = y2;
						vertices[C2] = color;
						vertices[U2] = u1;
						vertices[V2] = v2;

						vertices[X3] = x2;
						vertices[Y3] = y2;
						vertices[C3] = color;
						vertices[U3] = u2;
						vertices[V3] = v2;

						vertices[X4] = x2;
						vertices[Y4] = y1;
						vertices[C4] = color;
						vertices[U4] = u2;
						vertices[V4] = v1;

						if (flipX) {
							float temp = vertices[U1];
							vertices[U1] = vertices[U3];
							vertices[U3] = temp;
							temp = vertices[U2];
							vertices[U2] = vertices[U4];
							vertices[U4] = temp;
						}
						if (flipY) {
							float temp = vertices[V1];
							vertices[V1] = vertices[V3];
							vertices[V3] = temp;
							temp = vertices[V2];
							vertices[V2] = vertices[V4];
							vertices[V4] = temp;
						}
						if (rotations != 0) {
							switch (rotations) {
							case Cell.ROTATE_90: {
								float tempV = vertices[V1];
								vertices[V1] = vertices[V2];
								vertices[V2] = vertices[V3];
								vertices[V3] = vertices[V4];
								vertices[V4] = tempV;

								float tempU = vertices[U1];
								vertices[U1] = vertices[U2];
								vertices[U2] = vertices[U3];
								vertices[U3] = vertices[U4];
								vertices[U4] = tempU;
								break;
							}
							case Cell.ROTATE_180: {
								float tempU = vertices[U1];
								vertices[U1] = vertices[U3];
								vertices[U3] = tempU;
								tempU = vertices[U2];
								vertices[U2] = vertices[U4];
								vertices[U4] = tempU;
								float tempV = vertices[V1];
								vertices[V1] = vertices[V3];
								vertices[V3] = tempV;
								tempV = vertices[V2];
								vertices[V2] = vertices[V4];
								vertices[V4] = tempV;
								break;
							}
							case Cell.ROTATE_270: {
								float tempV = vertices[V1];
								vertices[V1] = vertices[V4];
								vertices[V4] = vertices[V3];
								vertices[V3] = vertices[V2];
								vertices[V2] = tempV;

								float tempU = vertices[U1];
								vertices[U1] = vertices[U4];
								vertices[U4] = vertices[U3];
								vertices[U3] = vertices[U2];
								vertices[U2] = tempU;
								break;
							}
							}
						}
						batch.draw(region.getTexture(), vertices, 0, 20);
						x += layerTileWidth;
					}
				}
				// And we're still subtracting.
				y -= layerTileHeight;
			}
		}
		
	}
	

}